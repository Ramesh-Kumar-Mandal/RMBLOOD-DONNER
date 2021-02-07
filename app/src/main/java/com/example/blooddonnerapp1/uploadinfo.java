package com.example.blooddonnerapp1;

public class uploadinfo {
    public String imageName;
    public String imageURL;

    public uploadinfo() {
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public uploadinfo(String imageName, String imageURL) {
        this.imageName = imageName;
        this.imageURL = imageURL;
    }
}
