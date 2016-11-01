package lanou.dllo.homework_tel.contacts;

import android.graphics.Bitmap;

/**
 * Created by dllo on 16/11/1.
 */
public class ContactBean {
    String name;
    String number;
    Bitmap image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
