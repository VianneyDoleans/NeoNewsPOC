package neonews.neonews;

import java.io.Serializable;

/**
 * Created by Guillaume on 12/03/2018.
 */

public class SubjectMedia implements Serializable {
    public enum MediaType {
        NEWSPAPER,
        VIDEO,
        RADIO
    }
    private String logoUrl;
    private String name;
    private MediaType mediaTye;
    private String url;
    private int times;

    public SubjectMedia(String logoUrl, String name, MediaType mediaTye, String url, int times) {
        this.logoUrl = logoUrl;
        this.name = name;
        this.mediaTye = mediaTye;
        this.url = url;
        this.times = times;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MediaType getMediaTye() {
        return mediaTye;
    }

    public void setMediaTye(MediaType mediaTye) {
        this.mediaTye = mediaTye;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
