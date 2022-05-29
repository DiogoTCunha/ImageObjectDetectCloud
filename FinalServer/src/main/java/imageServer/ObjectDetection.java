package imageServer;

import imagedetect.ImageObjects;
import imagedetect.Object;

public class ObjectDetection {
    String object;
    float score;

    public ObjectDetection(String object, float score) {
        this.object = object;
        this.score = score;
    }

    public ObjectDetection(){}

    //GETTERS AND SETTERS
    public String getObject() {
        return object;
    }

    public float getScore() {
        return score;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setScore(float score) {
        this.score = score;
    }
}