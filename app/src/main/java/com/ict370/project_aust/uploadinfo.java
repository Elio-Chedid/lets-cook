package com.ict370.project_aust;

public class uploadinfo {

    public String imageName;
    public String imageURL;
    public String imageDesc;
    public String uploadDate;
    public uploadinfo(){}

    public uploadinfo(String name, String url, String Desc,String upload) {

        this.imageName = name;
        this.imageURL = url;
        this.imageDesc=Desc;
        this.uploadDate=upload;
    }

    public String getImageName() {
        return imageName;
    }
    public String getImageURL() {
        return imageURL;
    }
    public String getImageDesc(){
        return imageDesc;
    }
    public String getUploadDate(){
        return uploadDate;
    }
}

