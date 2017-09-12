package com.alexfanning.artwaves.galleryitems;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 22-Aug-17.
 */

public class Gallery implements Parcelable {



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

    protected Gallery(Parcel in) {
        title = in.readString();
        imgSrc = in.readString();
        artist = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(imgSrc);
        dest.writeString(artist);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Gallery> CREATOR = new Parcelable.Creator<Gallery>() {
        @Override
        public Gallery createFromParcel(Parcel in) {
            return new Gallery(in);
        }

        @Override
        public Gallery[] newArray(int size) {
            return new Gallery[size];
        }
    };
}