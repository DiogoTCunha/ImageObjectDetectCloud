import com.google.cloud.ReadChannel;
import com.google.cloud.WriteChannel;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesRequest;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.BoundingPoly;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageSource;
import com.google.cloud.vision.v1.LocalizedObjectAnnotation;
import com.google.cloud.vision.v1.NormalizedVertex;
import com.google.gson.Gson;
import com.google.pubsub.v1.PubsubMessage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MessageReceiveHandler implements MessageReceiver {

    public static final Gson gson = new Gson();

    @Override
    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {
        System.out.println("Image Received");
        BlobData blob = gson.fromJson(pubsubMessage.getData().toString(StandardCharsets.UTF_8), BlobData.class);
        ObjectDetectionResult result = detectObjects(blob);
        publishToFirestore(result);
        ackReplyConsumer.ack();

    }

    public ObjectDetectionResult detectObjects (BlobData blobData){
        String gcsPath = "gs://" + blobData.bucketName + "/" + blobData.blobName;

        ImageSource imgSource = ImageSource.newBuilder().setGcsImageUri(gcsPath).build();
        Image img = Image.newBuilder().setSource(imgSource).build();

        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder()
                        .addFeatures(Feature.newBuilder().setType(Feature.Type.OBJECT_LOCALIZATION))
                        .setImage(img)
                        .build();

        BatchAnnotateImagesRequest singleBatchRequest = BatchAnnotateImagesRequest.newBuilder()
                .addRequests(request)
                .build();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ObjectDetectionResult result = new ObjectDetectionResult(blobData, Timestamp.from(Instant.now()));

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            // Perform the request
            BatchAnnotateImagesResponse batchResponse = client.batchAnnotateImages(singleBatchRequest);
            List<AnnotateImageResponse> listResponses = batchResponse.getResponsesList();

            if (listResponses.isEmpty()) {
                System.out.println("Empty response, no object detected.");
                return null;
            }

            // Add names and scores to object list
            AnnotateImageResponse response = listResponses.get(0);

            for (LocalizedObjectAnnotation annotation : response.getLocalizedObjectAnnotationsList())
                result.addObject(annotation.getName(), annotation.getScore());

            // annotate in memory Blob image
            BufferedImage bufferImg = getBlobBufferedImage(ObjectDetectApp.storage, BlobId.of(blobData.bucketName, blobData.blobName));
            annotateWithObjects(bufferImg, response.getLocalizedObjectAnnotationsList());
            // save the image to a new blob in the same bucket. The name of new blob has the annotated prefix
            writeAnnotatedImage(ObjectDetectApp.storage, bufferImg, blobData.bucketName, "annotated-" + blobData.blobName);

            System.out.println("Image processed");
            return result;

        } catch (IOException e) {
            System.out.println("IO Exception");
            return null;
        }
    }

    private static void writeAnnotatedImage(Storage storage, BufferedImage bufferImg, String bucketName, String destinationBlobName) throws IOException {
        BlobInfo blobInfo = BlobInfo
                .newBuilder(BlobId.of(bucketName, destinationBlobName))
                .setContentType("image/jpeg")
                .build();
        Blob destBlob = storage.create(blobInfo);
        WriteChannel writeChannel = storage.writer(destBlob);
        OutputStream out = Channels.newOutputStream(writeChannel);
        ImageIO.write(bufferImg, "jpg", out);
        out.close();
    }

    private static BufferedImage getBlobBufferedImage(Storage storage, BlobId blobId) throws IOException {
        Blob blob = storage.get(blobId);
        if (blob == null) {
            System.out.println("No such Blob exists !");
            throw new IOException("Blob <" + blobId.getName() + "> not found in bucket <" + blobId.getBucket() + ">");
        }
        ReadChannel reader = blob.reader();
        InputStream in = Channels.newInputStream(reader);
        return ImageIO.read(in);
    }

    public static void annotateWithObjects(BufferedImage img, List<LocalizedObjectAnnotation> objects) {
        for (LocalizedObjectAnnotation obj : objects) {
            annotateWithObject(img, obj);
        }
    }

    private static void annotateWithObject(BufferedImage img, LocalizedObjectAnnotation obj) {
        Graphics2D gfx = img.createGraphics();
        gfx.setFont(new Font("Arial", Font.PLAIN, 18));
        gfx.setStroke(new BasicStroke(3));
        gfx.setColor(new Color(0x00ff00));
        Polygon poly = new Polygon();
        BoundingPoly imgPoly = obj.getBoundingPoly();
        // draw object name
        gfx.drawString(obj.getName(),
                imgPoly.getNormalizedVertices(0).getX() * img.getWidth(),
                imgPoly.getNormalizedVertices(0).getY() * img.getHeight() - 3);
        // draw bounding box of object
        for (NormalizedVertex vertex : obj.getBoundingPoly().getNormalizedVerticesList()) {
            poly.addPoint((int) (img.getWidth() * vertex.getX()), (int) (img.getHeight() * vertex.getY()));
        }
        gfx.draw(poly);
    }


    public void publishToFirestore(ObjectDetectionResult result) {
        ObjectDetectApp.firestore.collection(ObjectDetectApp.FIRESTORE_COLLECTION)
                .document(result.blobData.blobName)
                .set(result);
    }

}
