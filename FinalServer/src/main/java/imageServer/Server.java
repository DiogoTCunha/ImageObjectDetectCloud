package imageServer;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import imagedetect.Image;
import imagedetect.ImageId;
import imagedetect.ImageIds;
import imagedetect.ImageObjects;
import imagedetect.RequestObjectDate;
import imagedetect.imageContractGrpc;
import io.grpc.ServerBuilder;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
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

    //TODO: RENAME TO GET OBJECTS
    @Override
    public void getLabels(ImageId request, StreamObserver<ImageObjects> responseObserver) {
        DocumentReference docRef = firestore.collection(FIRESTORE_COLLECTION).document(request.getId());
        ApiFuture<DocumentSnapshot> future= docRef.get();
        ObjectDetectionResult result;

        try {
            DocumentSnapshot doc = future.get();
            if (doc.exists()) {

                result = doc.toObject(ObjectDetectionResult.class);

                List<ObjectDetection> objects = result.getObjects();
                ImageObjects.Builder builder = ImageObjects.newBuilder();

                //TODO: Add score
                for (ObjectDetection object: objects) {
                    builder.addObjects(object.getObject());
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
        Date initialDate;
        Date endDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            initialDate = dateFormat.parse(request.getInitialDate());
            endDate = dateFormat.parse(request.getEndDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //TODO: Get images from firestore
    }

}
