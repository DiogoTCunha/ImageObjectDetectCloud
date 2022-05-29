import com.google.protobuf.ByteString;
import imagedetect.Image;
import imagedetect.ImageId;
import imagedetect.ImageIds;
import imagedetect.ImageObjects;
import imagedetect.RequestObjectDate;
import imagedetect.imageContractGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinalClient {

    private static String svcIP = "localhost";
    private static int svcPort = 8000;
    private static imageContractGrpc.imageContractStub noBlockingStub;
    private static imageContractGrpc.imageContractBlockingStub blockingStub;
    private static final Scanner in = new Scanner(System.in);;
    private static final String cloudFunctionURL = "https://us-central1-g08-t1d-v2021.cloudfunctions.net/funcIpLookup?instanceGroup=grpc-ig"; //TODO: Replace
    private static final int MAX_TRIES = 5;


    //TODO: Add getAnnotatedImage
    public static void main(String[] args) {

        try {
            ManagedChannel channel = ManagedChannelBuilder.forAddress(svcIP, svcPort).usePlaintext().build();

            noBlockingStub = imageContractGrpc.newStub(channel);
            blockingStub = imageContractGrpc.newBlockingStub(channel);

            System.out.println("Client Started with IP: " + svcIP + " and Port: " + svcPort);

            while(true) {
                switch (getOption()) {
                    case 0:
                        System.exit(0);
                    case 1:
                        uploadImage();
                        break;
                    case 2:
                        getImages();
                        break;
                    case 3:
                        getObjects();
                        break;
                    case 4:
                        downloadImage();
                        break;
                    default:
                        System.out.println("Not a valid operation");
                }
            }

        } catch (Exception ex) {
            System.out.println();
        }

    }

    //TODO: Check and use
    private static ManagedChannel getChannel() throws Exception {
        ManagedChannel channel;
        int tries = MAX_TRIES;
        List<String> ips = null;

        while(tries >= 0) {

            tries--;

            //Get the list of ips
            if(ips == null || ips.isEmpty()){

                HttpClient httpClient = HttpClient.newBuilder().build();
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .uri(URI.create(cloudFunctionURL))
                        .GET()
                        .build();

                HttpResponse<String> response = null;

                try {
                    response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(response != null && response.statusCode() == 200)
                    ips = new ArrayList<>(List.of(response.body().split("-")));
            }


            if (ips == null) {
                throw new Exception("IPs is null.");
            }
            svcIP = ips.remove((int) (Math.random() * ips.size()));

            //Return a random IP and remove it from the list.
            channel = ManagedChannelBuilder.forAddress(svcIP, svcPort)
                    // Channels are secure by default (via SSL/TLS).
                    // For the example we disable TLS to avoid needing certificates.
                    .usePlaintext()
                    .build();

            return channel;
        }

        throw new Exception("Couldn't get channel, max tries exceeded.");
    }

    public static int getOption(){
        System.out.println("1: Upload Image\n" +
                        "2: Get Images\n" +
                        "3: Get Objects\n" +
                        "4: Download Image\n" +
                        "0: Exit"
        );
        int option = in.nextInt();
        in.nextLine();
        return option;
    }

    public static void uploadImage(){
        /*
        System.out.println("Insert image path");
        String absFileName = in.nextLine();
        System.out.println("Insert extension");
        String imageExtension = in.nextLine();
        */

        String absFileName = "C:\\Users\\David\\Desktop\\test.jpg";
        String imageExtension = ".jpg";

        Path uploadFrom = Paths.get(absFileName);
        String fileName = uploadFrom.getFileName().toString();

        StreamObserver<Image> streamObserver = noBlockingStub.uploadImage(new ImageIdStreamObserver());

        try (InputStream input = Files.newInputStream(uploadFrom)) {
            byte[] buffer = new byte[1024];
            int limit;
            while ((limit = input.read(buffer)) >= 0) {
                ByteString bytes = ByteString.copyFrom(buffer, 0, limit);

                Image image = Image
                        .newBuilder()
                        .setFilename((fileName))
                        .setFileExtension(imageExtension)
                        .setImageBlockBytes(bytes)
                        .build();

                streamObserver.onNext(image);
            }
            streamObserver.onCompleted();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getImages() {

        RequestObjectDate request = getRequestObject();
        ImageIds images;

        try {
            images = blockingStub.getImages(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        int imageCount = images.getIdsCount();
        if (imageCount < 1) {
            System.out.println("No images were found.");
        } else {
            System.out.println("Found " + imageCount + " images\n");
            for (int i = 0; i < imageCount; i++)
                System.out.println(images.getIds(i));
        }
    }

    public static void downloadImage(){

        /*
        System.out.println("Insert the id");
        String id = in.nextLine();

        System.out.println("Insert the download path");
        String path = in.nextLine();
        */

        String id = "2022-05-29T18:08:31.833000000Ztest.jpg";
        String path = "A:\\Dropbox\\CN22\\";

        ImageId imageId = ImageId
                .newBuilder()
                .setId(id)
                .build();

        noBlockingStub.downloadImage(imageId, new ImageStreamObserver(path));
    }


    private static RequestObjectDate getRequestObject(){

        String object = "1D barcode";
        float score = 0.7381604313850403f;
        String initialDate = "2022-05-27";
        String endDate = "2023-01-01";

        /*
        System.out.println("Insert object name");
        String object = in.nextLine();

        System.out.println("Insert score");
        float score = in.nextFloat();
        in.nextLine();

        System.out.println("Insert initial date");
        String initialDate = in.nextLine();

        System.out.println("Insert end date");
        String endDate = in.nextLine();
        */

        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(initialDate);
            format.parse(endDate);
        } catch (ParseException e) {
            System.out.println("Date parsing exception: " + e.getMessage());
        }

        return RequestObjectDate
                .newBuilder()
                .setObject(object)
                .setScore(score)
                .setInitialDate(initialDate)
                .setEndDate(endDate)
                .build();
    }

    public static void getObjects(){
        /*
        System.out.println("Insert the image id");
        String id = in.nextLine();
        */

        String id = "2022-05-29T18:08:31.833000000Ztest.jpg";

        ImageId imageId = ImageId
                .newBuilder()
                .setId(id)
                .build();

        ImageObjects imageObjects;

        try {
            imageObjects = blockingStub.getObjects(imageId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        int objectCount = imageObjects.getObjectsCount();
        if (objectCount < 1) {
            System.out.println("No objects were found.");
        } else {
            System.out.println("Found " + objectCount + " objects\n");
            for (int i = 0; i < objectCount; i++)
                System.out.println(imageObjects.getObjects(i));
        }
    }

}
