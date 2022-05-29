package imageServer;

import com.google.cloud.firestore.annotation.ServerTimestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObjectDetectionResult {

    List<ObjectDetection> objects;
    BlobData blobData;

    @ServerTimestamp
    Date processingDate;

    public ObjectDetectionResult(BlobData blobData, Date processingDate) {
        this.objects = new ArrayList<>();
        this.blobData = blobData;
        this.processingDate = processingDate;
    }

    public ObjectDetectionResult(List<ObjectDetection> objects, BlobData blobData, Date processingDate) {
        this.objects = objects;
        this.blobData = blobData;
        this.processingDate = processingDate;
    }

    public ObjectDetectionResult(){}

    public void addObject(String object, float score){
        ObjectDetection objectDetection = new ObjectDetection(object, score);
        this.objects.add(objectDetection);
    }

    //GETTERS AND SETTERS
    public Date getProcessingDate() {
        return processingDate;
    }

    public BlobData getBlobData() {
        return blobData;
    }

    public List<ObjectDetection> getObjects() {
        return objects;
    }

    public void setProcessingDate(Date processingDate) {
        this.processingDate = processingDate;
    }

    public void setObjects(List<ObjectDetection> objects) {
        this.objects = objects;
    }

    public void setBlobData(BlobData blobData) {
        this.blobData = blobData;
    }

}
