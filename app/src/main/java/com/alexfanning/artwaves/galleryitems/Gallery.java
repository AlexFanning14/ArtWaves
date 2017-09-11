package com.alexfanning.artwaves.galleryitems;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 22-Aug-17.
 */

public class Gallery {



    private String title;
    private String imgSrc;
    private String artist;

    public Gallery(String title, String imgSrc, String artist) {
        this.title = title;
        this.imgSrc = imgSrc;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public String getArtist() {
        return artist;
    }
}
