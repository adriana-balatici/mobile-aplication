package com.example.adriana.myapplication2.domain;

/**
 * Created by Adriana on 11/25/2017.
 */

public class Restaurant {
    //private String id;
    private String name;
    private String location;
    private int stars;

    public Restaurant(){

    }

    public Restaurant(String name, String location, int stars){
        //this.id = id;
        this.name = name;
        this.location = location;
        this.stars = stars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }


    //public String getId() {
       // return id;
    //}

    //public void setId(String id) {
      //  this.id = id;
   // }
    @Override
    public String toString() {
        return  name + '\n' + location + '\n' + stars;
    }
}
