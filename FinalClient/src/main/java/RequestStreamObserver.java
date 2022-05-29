import imagedetect.ImageId;
import io.grpc.stub.StreamObserver;

public class RequestStreamObserver implements StreamObserver<ImageId> {

    private ImageId imageId = null;
    @Override
    public void onNext(ImageId imageId) {
        this.imageId = imageId;
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        System.out.println("Image sent");
        System.out.println("Id: " + imageId);
    }
}
