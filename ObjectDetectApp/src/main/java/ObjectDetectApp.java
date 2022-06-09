import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.pubsub.v1.ProjectSubscriptionName;
import io.grpc.LoadBalancerRegistry;
import io.grpc.internal.PickFirstLoadBalancerProvider;

import java.io.IOException;
import java.util.Scanner;

public class ObjectDetectApp {

    static Storage storage;
    static Firestore firestore;
    static String PROJECT_ID = "cn2122-t3-g07";
    static String SUBSCRIPTION_ID = "processing";
    static String FIRESTORE_COLLECTION = "DETECTION_RESULTS";
    static Subscriber subscriber;

    public static void main(String[] args) {
        initCloudFunctions();
        subscriber = subscribeToMessages();

        System.out.println("Running and waiting for Images");


        while(true) {

        }

    }

    public static void initCloudFunctions() {
        LoadBalancerRegistry.getDefaultRegistry().register(new PickFirstLoadBalancerProvider());
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

    public static Subscriber subscribeToMessages() {
        ProjectSubscriptionName projSubscriptionName = ProjectSubscriptionName.of(
                PROJECT_ID, SUBSCRIPTION_ID);
        Subscriber subscriber =
                Subscriber.newBuilder(projSubscriptionName, new MessageReceiveHandler())
                        .build();
        subscriber.startAsync().awaitRunning();
        return subscriber;
    }
}

