package lanou.dllo.homework_tel.callrecords;

/**
 * Created by dllo on 16/10/25.
 */
public class MyBean {
    String name;
    String number;
    String date;
    int image;

    public int getImage() {
        return image;
    }

    public MyBean setImage(int image) {
        this.image = image;
        return this;
    }

    public MyBean(int image) {
        this.image = image;
    }

    public MyBean(String name, String number, String date) {
        this.name = name;
        this.number = number;
        this.date = date;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
