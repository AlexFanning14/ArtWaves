package com.alexfanning.artwaves.galleryitems;

import android.graphics.drawable.Drawable;

/**
 * Created by User on 22-Aug-17.
 */

public class Gallery {




    private String title;
    private Drawable img;
    private String artist;
    private String descript;

    public Gallery(String title, Drawable img, String artist, String descript) {
        this.title = title;
        this.img = img;
        this.artist = artist;
        this.descript = descript;
    }

    public String getTitle() {
        return title;
    }

    public Drawable getImg() {
        return img;
    }

    public String getArtist() {
        return artist;
    }

    public String getDescript() {
        return descript;
    }




}
