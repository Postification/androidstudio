package com.postification.postification;

public class Baggage {

    long id;
    private String name;
    private int weight;
    private String time;

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

    public int getWeight(){
        return weight;
    }

    public void setWeight(int weight){
        this.weight=weight;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time=time;
    }
}
