package com.alexfanning.artwaves.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alexfanning.artwaves.R;
import com.alexfanning.artwaves.venueitems.Venue;
import com.alexfanning.artwaves.venueitems.VenueDataAdapter;
import com.alexfanning.artwaves.venueitems.VenueLoader;


/**
 * A simple {@link Fragment} subclass.

 */
public class ArttrailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Venue[]> {
    public ArttrailFragment(){}

    private Context mContext;
    private RecyclerView mVenueRv;
    private VenueDataAdapter mVenueDatapter;
    private ProgressBar mProgBar;
    private TextView mTextViewError;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_arttrail,container,false);

        findViews(rootView);

        mVenueRv.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        mVenueRv.setHasFixedSize(true);
        setUpVenues();
        return rootView;
    }
    private void findViews(View rootview){

        mVenueRv = (RecyclerView) rootview.findViewById(R.id.rv_venues);
        mProgBar = (ProgressBar) rootview.findViewById(R.id.ven_progbar);
        mTextViewError = (TextView) rootview.findViewById(R.id.tv_error_venue);
    }

    @Override
    public Loader<Venue[]> onCreateLoader(int id, Bundle args) {
        mProgBar.setVisibility(View.VISIBLE);
        return new VenueLoader(mContext);

    }


    private void setUpVenues(){
        getLoaderManager().initLoader(VenueLoader.VENUE_LOADER_ID,null,this);
        LoaderManager lm = getLoaderManager();
        Loader<Venue[]> venueLoader = lm.getLoader(VenueLoader.VENUE_LOADER_ID);
        if (venueLoader == null){
            lm.initLoader(VenueLoader.VENUE_LOADER_ID,null,this);
        }else{
            lm.restartLoader(VenueLoader.VENUE_LOADER_ID,null,this).forceLoad();
        }

    }


    @Override
    public void onLoadFinished(Loader<Venue[]> loader, Venue[] venues) {
        mProgBar.setVisibility(View.GONE);
        if (venues == null){
            mTextViewError.setVisibility(View.VISIBLE);
            mVenueRv.setVisibility(View.INVISIBLE);
        }else if (venues.length == 0){
            //There was no venues
        }else{
            mTextViewError.setVisibility(View.GONE);
            mVenueRv.setVisibility(View.VISIBLE);
            mVenueDatapter = new VenueDataAdapter(venues,mContext);
            mVenueRv.setAdapter(mVenueDatapter);
        }
    }


    @Override
    public void onLoaderReset(Loader<Venue[]> loader) {
//        Do nothing
    }




}
