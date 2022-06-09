import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;

public class MessageReceiveHandler implements MessageReceiver {

    private static int instances = 0;
    private static final double lowerThreshold = 0.03;
    private static final double upperThreshold = 0.07;
    private static final MessageFreqCounter freqCounter = new MessageFreqCounter(60*1000);

    @Override
    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {

        freqCounter.increment();
        double requestsPerMin = freqCounter.getCount();
        double ref = requestsPerMin / 60;

        if(ref > lowerThreshold)
            addInstance();
        if(ref < upperThreshold)
            removeInstance();

        ackReplyConsumer.ack();
    }

    private static void addInstance(){
        if(instances < 4) {
            instances++;
            launchInstance();
        }

    }

    private static void removeInstance(){
        if(instances > 1){
            instances--;
            closeInstance();
        }
    }

    private static void launchInstance(){
        //TODO: Implement launch
    }
    private static void closeInstance(){
        //TODO: Implement close
    }

    private static void resizeManagedInstanceGroup(String project, String zone, String instanceGroupName,
                               int newSize) {
        System.out.println("================== Resizing instance group");
        InstanceGroupManagersClient managersClient = InstanceGroupManagersClient.create();
        OperationFuture<Operation, Operation> result = managersClient.resizeAsync(
                project,
                zone,
                instanceGroupName,
                newSize
        );
        Operation oper=result.get();
        System.out.println("Resizing with status " + oper.getStatus().toString());
    }


}
