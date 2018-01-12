package com.thpa.a9019.fitnessmore.model;

/**
 * Created by COM on 13/4/2560.
 */

public class TypeTrain {
    private  int id;
    private String name;
    private String img;
    private String type;

    private String des;

    public TypeTrain(int id, String name, String img) {
        this.id  = id;
        this.name = name;
        this.img = img;
    }
    public TypeTrain(String type, String img, String des) {
        this.type  = type;
        this.img = img;
        this.des = des;
    }
    public TypeTrain(String name, String img) {

        this.name = name;
        this.img = img;
    }



    public  TypeTrain(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {return img;}

    public void setImg(String name) {this.img = img;}

    public String getDes() {return des;}

    public void setDes(String des) {this.des = des;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

}
