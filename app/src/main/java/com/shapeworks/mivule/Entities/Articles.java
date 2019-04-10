package com.shapeworks.mivule.Entities;

import android.net.Uri;

/**
 * Created by KoomaBenjamin on 31/10/2017.
 */

public class Articles {
    public String icon, news_image, status, author, key;

    //public int statusImg_uri, iconImg_uri;
    public String st_img, iconx;

    public Articles() {
    }

    public Articles(String title, String genre, String year) {
        this.icon = title;
        this.status = genre;
        this.news_image = year;

    }
//    public Articles(String status, int statusImg, int icon) {
//        this.status = status;
//        this.statusImg_uri = statusImg;
//        this.iconImg_uri = icon;
//
//    }
    public Articles(String status, String author, String statusImg, String icon) {
        this.status = status;
        this.st_img = statusImg;
        this.iconx = icon;
        this.author = author;

    }
}
