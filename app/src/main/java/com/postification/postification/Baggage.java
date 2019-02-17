package com.postification.postification;

public class Baggage {

    long id;
    private String name;
    private String weight;
    private String time;
    private int intWeight;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getWeight(){
        return weight;
    }

    public void setWeight(String weight){
        this.weight=weight;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time=time;
    }

    public int getIntWeight(){
        return intWeight;
    }

    public void setIntWeight(int weight){
        this.intWeight=weight;
    }
}
