import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;

import java.util.Scanner;

public class MonitorApp {
    static String PROJECT_ID = "CN2122-T3-G07";
    static String SUBSCRIPTION_ID = "monitoring";
    static Subscriber subscriber;

    public static void main(String[] args) {

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
