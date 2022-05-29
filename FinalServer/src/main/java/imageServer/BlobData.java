package imageServer;

public class BlobData {

    String id ;
    String bucketName;
    String blobName;
    String serverIp;

    public BlobData(String id, String bucketName, String blobName, String serverIp) {
        this.id = id;
        this.bucketName = bucketName;
        this.blobName = blobName;
        this.serverIp = serverIp;
    }

    public BlobData(){}

    public String getId() {
        return id;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getBlobName() {
        return blobName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void setBlobName(String blobName) {
        this.blobName = blobName;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }
}
