package com.shapeworks.mivule.Entities;

/**
 * Created by KoomaBenjamin on 17/10/2017.
 */

public class Consultant {

    private String name;
    private String email;
    private String expertise;
    private float rating;

    public Consultant() {
    }

    public Consultant(String name, String email, String expertise, float rating) {
        this.name = name;
        this.email = email;
        this.expertise = expertise;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
