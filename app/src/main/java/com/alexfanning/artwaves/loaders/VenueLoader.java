package com.alexfanning.artwaves.loaders;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.alexfanning.artwaves.Venue;
import com.alexfanning.artwaves.utils.JsonUtils;

/**
 * Created by alexfanning on 25/07/2017.
 */

public class VenueLoader extends AsyncTaskLoader<Venue[]> {
    public static final int VENUE_LOADER_ID = 20;
    private static final String TAG = VenueLoader.class.getSimpleName();
    private Context c;

    public VenueLoader(Context c){ super(c);}



    @Override
    public Venue[] loadInBackground() {
        Venue[] vens = JsonUtils.getVens();
        return vens;
    }


}
