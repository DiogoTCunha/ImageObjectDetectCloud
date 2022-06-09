import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import io.grpc.LoadBalancerRegistry;
import io.grpc.internal.PickFirstLoadBalancerProvider;

import java.util.Scanner;

public class MonitorApp {
    static String PROJECT_ID = "cn2122-t3-g07";
    static String SUBSCRIPTION_ID = "monitoring";
    static String ZONE = "europe-west2-c";
    static String INSTANCE_GROUP = "detect-group";
    static Subscriber subscriber;

    public static void main(String[] args) {

        LoadBalancerRegistry.getDefaultRegistry().register(new PickFirstLoadBalancerProvider());
        subscriber = addSubscription();
        
        System.out.println("Monitor app started");
        System.out.println("Type \"exit\" to close");

        Scanner in = new Scanner(System.in);
        String line;

        while(true) {
            line = in.nextLine();
            if(line.equals("exit"))
                System.exit(0);
        }
    }

    public static Subscriber addSubscription(){
        ProjectSubscriptionName projSubscriptionName = ProjectSubscriptionName.of(PROJECT_ID, SUBSCRIPTION_ID);
        Subscriber subscriber = Subscriber.newBuilder(projSubscriptionName, new MessageReceiveHandler())
                .build();
        subscriber.startAsync().awaitRunning();
        return subscriber;
    }
}
