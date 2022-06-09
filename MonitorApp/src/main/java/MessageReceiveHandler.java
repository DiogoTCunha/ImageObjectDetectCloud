import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.compute.v1.InstanceGroupManagersClient;
import com.google.cloud.compute.v1.ListManagedInstancesInstanceGroupManagersRequest;
import com.google.cloud.compute.v1.ManagedInstance;
import com.google.cloud.compute.v1.Operation;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;

import java.io.IOException;

public class MessageReceiveHandler implements MessageReceiver {

    private static int instances;
    private static final double lowerThreshold = 0.03;
    private static final double upperThreshold = 0.07;
    private static final MessageFreqCounter freqCounter = new MessageFreqCounter(60*1000);

    public MessageReceiveHandler(){
        instances = getInstanceCount();
    }

    @Override
    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {

        freqCounter.increment();
        double requestCount = freqCounter.getCount();
        double ref = requestCount / 60;

        if(ref > upperThreshold)
            addInstance();
        if(ref < lowerThreshold)
            removeInstance();

        ackReplyConsumer.ack();
    }

    private static void addInstance(){
        if(instances < 4) {
            instances++;
            resizeManagedInstanceGroup(instances);
        }
    }

    private static void removeInstance(){
        if(instances > 1){
            instances--;
            resizeManagedInstanceGroup(instances);
        }
    }

    private static void resizeManagedInstanceGroup(int size) {
        System.out.println("Resizing instance group to: " + size);

        InstanceGroupManagersClient managersClient = null;
        Operation op = null;
        try {
            managersClient = InstanceGroupManagersClient.create();
            OperationFuture<Operation, Operation> result = managersClient.resizeAsync(
                    MonitorApp.PROJECT_ID,
                    MonitorApp.ZONE,
                    MonitorApp.INSTANCE_GROUP,
                    size
            );
            op = result.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert op != null;
        System.out.println("Resizing with status " + op.getStatus());
    }

    private static int getInstanceCount(){
        InstanceGroupManagersClient managersClient = null;
        try {
            managersClient = InstanceGroupManagersClient.create();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ListManagedInstancesInstanceGroupManagersRequest request =
                ListManagedInstancesInstanceGroupManagersRequest.newBuilder()
                        .setInstanceGroupManager(MonitorApp.INSTANCE_GROUP)
                        .setProject(MonitorApp.PROJECT_ID)
                        .setReturnPartialSuccess(true)
                        .setZone(MonitorApp.ZONE)
                        .build();

        int instanceCount = 0;

        for (ManagedInstance instance: managersClient.listManagedInstances(request).iterateAll()){
            instanceCount++;
        }

        return instanceCount;
    }
}
