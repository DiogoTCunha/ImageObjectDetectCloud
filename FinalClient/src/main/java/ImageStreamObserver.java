import imagedetect.Image;
import io.grpc.stub.StreamObserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ImageStreamObserver implements StreamObserver<Image> {

    FileChannel fc = null;
    String downloadPath;

    public ImageStreamObserver(String downloadPath){
        this.downloadPath = downloadPath;
    }

    @Override
    public void onNext(Image image) {

        if(fc == null) {
            try {
                //File names cannot contain ":"
                String completePath = downloadPath + "\\" +image.getFilename().replace(":", "-");
                fc = new FileOutputStream(completePath).getChannel();
            } catch (FileNotFoundException e) {
                System.out.println("File Exception");
            }
        }

        ByteBuffer byteBuffer = image.getImageBlockBytes().asReadOnlyByteBuffer();
        try {
            fc.write(byteBuffer);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }

    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
        try {
            if(fc != null)
                fc.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onCompleted() {
        try {
            fc.close();
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        System.out.println("File download completed");
    }
}
