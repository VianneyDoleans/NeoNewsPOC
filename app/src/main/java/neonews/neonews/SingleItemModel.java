package neonews.neonews;

public class SingleItemModel {

    private String name;
    private String imageUrl;
    private String description;

    public SingleItemModel() {
    }

    public SingleItemModel(String name, String url) {
        this.name = name;
        this.imageUrl = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}