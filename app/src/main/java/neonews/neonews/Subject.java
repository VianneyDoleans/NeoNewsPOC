package neonews.neonews;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Guillaume on 12/03/2018.
 */

public class Subject implements Serializable {
    String imageUrl;
    String title;
    String description;
    double x;
    double y;
    List<SubjectMedia> listMedia;

    public Subject(String imageUrl, String title, String description, List<SubjectMedia> listMedia, double x, double y) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.listMedia = listMedia;
        this.x = x;
        this.y = y;
    }

    public List<SubjectMedia> getListMedia() {
        return listMedia;
    }

    public void setListMedia(List<SubjectMedia> listMedia) {
        this.listMedia = listMedia;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getX() { return x; }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() { return y; }

    public void setY(double y) {
        this.y = y;
    }
}
