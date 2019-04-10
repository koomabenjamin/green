package com.shapeworks.mivule.Entities;

/**
 * Created by KoomaBenjamin on 17/10/2017.
 */

public class Sites {
    private String ownerEmail;
    private String createrEmail;
    private String projectReference;
    private String[] sitePoints;

    public Sites() {
    }

    public Sites(String ownerEmail, String createrEmail, String projectReference, String[] sitePoints) {
        this.ownerEmail = ownerEmail;
        this.createrEmail = createrEmail;
        this.projectReference = projectReference;
        this.sitePoints = sitePoints;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getCreaterEmail() {
        return createrEmail;
    }

    public String getProjectReference() {
        return projectReference;
    }

    public String[] getSitePoints() {
        return sitePoints;
    }
}

