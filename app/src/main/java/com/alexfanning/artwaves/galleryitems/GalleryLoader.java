package com.alexfanning.artwaves.galleryitems;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.alexfanning.artwaves.utils.JsonUtils;

/**
 * Created by User on 11-Sep-17.
 */

public class GalleryLoader extends AsyncTaskLoader<Gallery[]> {
    public static final int GALLERY_LOADER_ID = 21;
    private static final String TAG = GalleryLoader.class.getSimpleName();
    private Context mContext;
    public GalleryLoader(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Gallery[] loadInBackground() {
        return JsonUtils.getGallery();
    }
}
