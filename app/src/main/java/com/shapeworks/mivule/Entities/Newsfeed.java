package com.shapeworks.mivule.Entities;

/**
 * Created by Kooma Benamin on 2/27/2018.
 */

public class Newsfeed {

    private String icon;
    private String title;
    private String brief;
    private String likes;
    private String comments;
    private String shares;
    private String downloadUrls;

    public Newsfeed() {
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public String getDownloadUrls() {
        return downloadUrls;
    }

    public void setDownloadUrls(String downloadUrls) {
        this.downloadUrls = downloadUrls;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }
}
