package com.alexfanning.artwaves.galleryitems;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 22-Aug-17.
 */

public class Gallery implements Parcelable {

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

    protected Gallery(Parcel in) {
        title = in.readString();
        img = (Drawable) in.readValue(Drawable.class.getClassLoader());
        artist = in.readString();
        descript = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeValue(img);
        dest.writeString(artist);
        dest.writeString(descript);
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
