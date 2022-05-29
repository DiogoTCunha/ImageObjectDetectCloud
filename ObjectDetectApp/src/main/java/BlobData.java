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
}