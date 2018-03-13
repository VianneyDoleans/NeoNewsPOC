package neonews.neonews;

import java.util.List;

/**
 * Created by Guillaume on 12/03/2018.
 */

public class Subject {
    String imageUrl;
    String title;
    String description;
    List<SubjectMedia> listMedia;

    public Subject(String imageUrl, String title, String description, List<SubjectMedia> listMedia) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.listMedia = listMedia;
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
}
