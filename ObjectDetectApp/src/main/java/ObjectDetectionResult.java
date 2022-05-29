import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObjectDetectionResult {

    List<ObjectDetection> objects;
    BlobData blobData;
    Date processingDate;

    public ObjectDetectionResult(BlobData blobData, Date processingDate) {
        this.objects = new ArrayList<>();
        this.blobData = blobData;
        this.processingDate = processingDate;
    }

    public void addObject(String object, float score){
        ObjectDetection objectDetection = new ObjectDetection(object, score);
        this.objects.add(objectDetection);
    }

    public Date getProcessingDate() {
        return processingDate;
    }

    public BlobData getBlobData() {
        return blobData;
    }

    public List<ObjectDetection> getObjects() {
        return objects;
    }

    private class ObjectDetection {
        String object;
        float score;

        public ObjectDetection(String object, float score) {
            this.object = object;
            this.score = score;
        }

        public String getObject() {
            return object;
        }

        public float getScore() {
            return score;
        }
    }
}
