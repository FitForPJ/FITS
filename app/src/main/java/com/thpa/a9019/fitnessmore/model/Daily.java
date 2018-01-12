package com.thpa.a9019.fitnessmore.model;

/**
 * Created by 9019 on 26/8/2560.
 */

public class Daily {

    private int id;
    private String date;
    private String name;
    private int time;

    public Daily(int id,String date,String name,int time) {
       this.id = id;
        this.date = date;
        this.name = name;
        this.time  = time;
    }
    public Daily(String date,int time) {

        this.date = date;
        this.time  = time;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
