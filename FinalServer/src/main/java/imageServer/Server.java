package imageServer;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.protobuf.ByteString;
import imagedetect.Image;
import imagedetect.ImageId;
import imagedetect.ImageIds;
import imagedetect.ImageObjects;
import imagedetect.Object;
import imagedetect.RequestObjectDate;
import imagedetect.imageContractGrpc;
import io.grpc.ServerBuilder;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Server extends imageContractGrpc.imageContractImplBase {

    static Storage storage;
    static Firestore firestore;
    static String BUCKET_ID = "img-detect-bucket";
    static String PROJECT_ID = "cn2122-t3-g07";
    static String TOPIC_ID = "detectionworkers";
    static String FIRESTORE_COLLECTION = "DETECTION_RESULTS";
    private static final int DEFAULT_PORT = 8000;

    public static void main(String[] args) {
        initCloudFunctions();

        try {
            io.grpc.Server svc = ServerBuilder.forPort(DEFAULT_PORT).addService(new Server()).build();
            svc.start();
            System.out.println("Server started, listening on " + DEFAULT_PORT);
            svc.awaitTermination();
            svc.shutdown();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void initCloudFunctions() {
        GoogleCredentials credentials = null;
        try {
            credentials = GoogleCredentials.getApplicationDefault();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StorageOptions storageOptions = StorageOptions.newBuilder().setCredentials(credentials).build();
        storage = storageOptions.getService();

        FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance();
        firestore = firestoreOptions.getService();
    }

    @Override
    public StreamObserver<Image> uploadImage(StreamObserver<ImageId> responseObserver) {
        return new StreamImage(responseObserver);
    }

    @Override
    public void downloadImage(ImageId imageId, StreamObserver<Image> responseObserver){
        BlobId blobId = BlobId.of(BUCKET_ID, "annotated-" + imageId.getId());
        Blob blob = storage.get(blobId);

        try (ReadChannel reader = blob.reader()) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while ((reader.read(buffer)) > 0) {

                buffer.flip();
                ByteString bytes = ByteString.copyFrom(buffer);
                Image image = Image.newBuilder()
                        .setFilename(imageId.getId())
                        .setFileExtension(".jpg") //TODO: Maybe remove extension?
                        .setImageBlockBytes(bytes).build();
                responseObserver.onNext(image);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        responseObserver.onCompleted();
    }

    @Override
    public void getObjects(ImageId request, StreamObserver<ImageObjects> responseObserver) {
        DocumentReference docRef = firestore.collection(FIRESTORE_COLLECTION).document(request.getId());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        ObjectDetectionResult result;

        try {
            DocumentSnapshot doc = future.get();
            if (doc.exists()) {

                result = doc.toObject(ObjectDetectionResult.class);

                // TODO: If objects in firestore is null
                List<ObjectDetection> objects = result.getObjects();

                ImageObjects.Builder builder = ImageObjects.newBuilder();
                for (ObjectDetection object: objects) {
                    builder.addObjects(Object.newBuilder()
                            .setObject(object.object)
                            .setScore(object.score)
                            .build());
                }
                responseObserver.onNext(builder.build());

                responseObserver.onCompleted();
            } else {

                responseObserver.onError(new StatusException(Status.NOT_FOUND.withDescription("No such Document")));

            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getImages(RequestObjectDate request, StreamObserver<ImageIds> responseObserver) {

        String object = request.getObject();
        float score = request.getScore();
        Date initialDate;
        Date endDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            initialDate = dateFormat.parse(request.getInitialDate());
            endDate = dateFormat.parse(request.getEndDate());
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        ImageIds.Builder builder = ImageIds.newBuilder();

        Query query = firestore
                .collection(FIRESTORE_COLLECTION)
                .whereGreaterThanOrEqualTo("processingDate", initialDate)
                .whereLessThanOrEqualTo("processingDate", endDate);

        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        try {
            for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
                ObjectDetectionResult result = doc.toObject(ObjectDetectionResult.class);
                for (ObjectDetection obj : result.objects) {
                    // TODO: Carefully with floats comparison
                    if (obj.object.equals(object) && obj.score >= score) {
                        builder.addIds(doc.getId());
                        break;
                    }
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

}
