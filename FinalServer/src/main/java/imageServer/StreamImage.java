package imageServer;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.WriteChannel;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import imagedetect.Image;
import imagedetect.ImageId;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

import static imageServer.Server.BUCKET_ID;
import static imageServer.Server.PROJECT_ID;
import static imageServer.Server.TOPIC_ID;
import static imageServer.Server.storage;

public class StreamImage implements StreamObserver<Image> {

    private final StreamObserver<ImageId> responseObserver;
    private WriteChannel writer = null;
    private BlobId blobId = null;
    private String filename = null;
    private static final Gson gson = new Gson();

    public StreamImage(StreamObserver<ImageId> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public void onNext(Image image) {
        if (writer == null) {
            filename = Timestamp.now() + image.getFilename();
            blobId = BlobId.of(BUCKET_ID, filename);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(image.getFileExtension()).build();
            writer = storage.writer(blobInfo);
        }

        ByteBuffer byteBuffer = image.getImageBlockBytes().asReadOnlyByteBuffer();

        try {
            writer.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        if (writer == null && blobId == null) {
            return;
        }

        if (storage != null) storage.delete(blobId);

        try {
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        if (writer == null)
            throw new RuntimeException("Something went wrong.");

        String id = filename;
        ImageId result = ImageId
                .newBuilder()
                .setId(id)
                .build();
        responseObserver.onNext(result);

        String hostAddress;
        try {
            hostAddress = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            hostAddress = "Unknown";
        }

        BlobData blob = new BlobData(id, BUCKET_ID, filename, hostAddress);

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sendToPubSub(blob);
        responseObserver.onCompleted();
    }

    private static void sendToPubSub(BlobData blob) {

        TopicName tName = TopicName.ofProjectTopicName(PROJECT_ID, TOPIC_ID);
        Publisher publisher;
        try {
            publisher = Publisher.newBuilder(tName).build();
        } catch (IOException e) {
            System.out.println("Error Creating Publisher");
            return;
        }

        ByteString msgData = ByteString.copyFromUtf8(gson.toJson(blob));
        System.out.println(msgData);

        PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                .setData(msgData)
                .build();

        ApiFuture<String> future = publisher.publish(pubsubMessage);

        String msgID = null;
        try {
            msgID = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Message Published with ID= " + msgID);
        publisher.shutdown();
    }
}
