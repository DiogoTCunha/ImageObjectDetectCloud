
mport com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import java.io.BufferedWriter;

import com.google.cloud.compute.v1.Instance;
import com.google.cloud.compute.v1.InstancesClient;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LookupService implements HttpFunction {
    static private final String PROJECT_NAME = "cn2122-t3-g07";
    static private final String ZONE = "europe-west2-c";

    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {

        //Get instance group name
        List<String> instanceGroupNames = httpRequest.getQueryParameters().get("instanceGroup");

        if(instanceGroupNames == null || instanceGroupNames.isEmpty()) {
            httpResponse.setStatusCode(400);
            return;
        }

        //Get the list of ips and return it in the response
        String instanceGroupName = instanceGroupNames.get(0);
        List<String> ips = listVMInstances(PROJECT_NAME, ZONE, instanceGroupName);

        httpResponse.setStatusCode(200);
        BufferedWriter writer = httpResponse.getWriter();
        writer.write(String.join("-", ips));
        writer.close();
    }

    static List<String> listVMInstances(String project, String zone, String instanceGroupName) throws IOException {
        List<String> ips = new ArrayList<>();
        try (InstancesClient client = InstancesClient.create()) {
            for (Instance instance : client.list(project, zone).iterateAll()) {
                if (instance.getStatus().equals("RUNNING") && instance.getName().startsWith(instanceGroupName)) {
                    ips.add(instance.getNetworkInterfaces(0).getAccessConfigs(0).getNatIP());
                }
            }
        }
        return ips;
    }
}