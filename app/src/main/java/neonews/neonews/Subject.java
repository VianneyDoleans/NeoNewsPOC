package neonews.neonews;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Guillaume on 12/03/2018.
 */

public class Subject implements Serializable {
    private String title;
    private String description;
    private Date date;
    private String imageUrl;
    private double lat;
    private double lng;
    private List<SubjectMedia> listMedia;

    public Subject(String imageUrl, String title, String description, double lat, double lng, Date date) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.lat = lat;
        this.lng = lng;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public double getLat() { return lat; }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() { return lng; }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
