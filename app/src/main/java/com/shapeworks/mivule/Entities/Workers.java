package com.shapeworks.mivule.Entities;

/**
 * Created by Kooma Benamin on 2/27/2018.
 */

public class Workers {

    private String icon;
    private String name;
    private String expertise;
    private String email;
    private String availability;
    private String intro;
    private String experience;

    public Workers() {
    }

    public Workers(String icon, String name, String expertise, String email, String availability, String intro, String experience) {
        this.icon = icon;
        this.name = name;
        this.expertise = expertise;
        this.email = email;
        this.availability = availability;
        this.intro = intro;
        this.experience = experience;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
