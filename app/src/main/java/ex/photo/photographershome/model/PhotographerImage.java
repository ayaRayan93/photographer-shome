package ex.photo.photographershome.model;

import java.io.Serializable;

public class PhotographerImage  implements Serializable {

    String Catagory;
    String Image;

    public String getCatagory() {
        return Catagory;
    }

    public void setCatagory(String catagory) {
        Catagory = catagory;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
