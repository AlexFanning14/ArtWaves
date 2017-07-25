package com.alexfanning.artwaves;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexfanning.artwaves.utils.JsonUtils;


/**
 * A simple {@link Fragment} subclass.

 */
public class ArttrailFragment extends Fragment {
    public ArttrailFragment(){}

    private Context mContext;
    private RecyclerView mVenueRv;
    private VenueDataAdapter mVenueDatapter;



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
    }

    private void setUpVenues(){
        Venue[] vens = JsonUtils.getVens();
//        Venue[] vens = new Venue[2];
//        vens[0] = new Venue("ArtPark 2017","Sculpture Garden, Ardgillan Castle","A new feature in conjunction with Ardgillan Castle, we are creating a temporary Sculpture Garden within the Castle grounds. Works for this will be chosen through the ArtWaves Open-Submission.","1000");
//        vens[1] = new Venue("RunOfTheMill 2017","ArtWaves Exhibition, Skerries Mills","This exhibition has been running since the inception of SoundWaves Festival in 2004, and is also the starting point for our ArtTrail Guided Tours/Walk.","1000");
        mVenueDatapter = new VenueDataAdapter(vens,mContext);
        mVenueRv.setAdapter(mVenueDatapter);


    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
