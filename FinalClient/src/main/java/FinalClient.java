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

    private static String svcIP;
    private static final int svcPort = 8000;
    private static imageContractGrpc.imageContractStub noBlockingStub;
    private static imageContractGrpc.imageContractBlockingStub blockingStub;
    private static final Scanner in = new Scanner(System.in);
    private static final String cloudFunctionURL = "https://europe-west2-cn2122-t3-g07.cloudfunctions.net/lookup-function?instanceGroup=server-group";

    public static void main(String[] args) {

        try {
            ManagedChannel channel = getChannel();



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
            System.out.println(ex.getMessage());
        }

    }

    private static ManagedChannel getChannel() throws Exception {
        ManagedChannel channel;
        List<String> ips;

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

        if(response != null && response.statusCode() == 200) {

            ips = new ArrayList<>(List.of(response.body().split("-")));

            System.out.println("IP List:");
            for(int i = 0; i < ips.size(); i++){
                System.out.println(i + "-> " + ips.get(i));
            }

            System.out.println("Select an IP with the number:");
            int option = in.nextInt();
            svcIP = ips.get(option);

            channel = ManagedChannelBuilder.forAddress(svcIP, svcPort)
                    .usePlaintext()
                    .build();
            return channel;
        }

        throw new Exception("Couldn't get channel.");
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

        System.out.println("Insert full image path");
        String absFileName = in.nextLine();
        System.out.println("Insert extension");
        String imageExtension = in.nextLine();

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

        System.out.println("Insert the id");
        String id = in.nextLine();

        System.out.println("Insert the download path");
        String path = in.nextLine();

        ImageId imageId = ImageId
                .newBuilder()
                .setId(id)
                .build();

        noBlockingStub.downloadImage(imageId, new ImageStreamObserver(path));
    }

    private static RequestObjectDate getRequestObject(){

        System.out.println("Insert object name");
        String object = in.nextLine();

        System.out.println("Insert score");
        float score = in.nextFloat();
        in.nextLine();

        System.out.println("Insert initial date (yyyy-MM-dd)");
        String initialDate = in.nextLine();

        System.out.println("Insert end date (yyyy-MM-dd)");
        String endDate = in.nextLine();


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

        System.out.println("Insert the image id");
        String id = in.nextLine();

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
