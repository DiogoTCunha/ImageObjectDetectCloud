import imagedetect.Image;
import imagedetect.ImageId;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.nio.ByteBuffer;

public class StreamImage implements StreamObserver<Image> {

    private final StreamObserver<ImageId> responseObserver;

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
        System.out.println(throwable.getMessage());
    }

    @Override
    public void onCompleted() {

    }
}
