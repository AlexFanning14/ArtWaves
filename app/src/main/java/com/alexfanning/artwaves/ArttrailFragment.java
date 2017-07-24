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

        mVenueDatapter = new VenueDataAdapter(vens,mContext);
        mVenueRv.setAdapter(mVenueDatapter);


    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
