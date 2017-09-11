package com.alexfanning.artwaves.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alexfanning.artwaves.R;
import com.alexfanning.artwaves.galleryitems.Gallery;
import com.alexfanning.artwaves.galleryitems.GalleryLoader;
import com.alexfanning.artwaves.venueitems.VenueDataAdapter;

public class GalleryFragment extends Fragment implements LoaderManager.LoaderCallbacks<Gallery[]> {

    private Context mContext;
    private RecyclerView mGalleryRv;
    private VenueDataAdapter mGalleryDatapter;
    private ProgressBar mProgBarGallery;
    private TextView mTextViewError;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        findViews(rootView);

        mGalleryRv.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        mGalleryRv.setHasFixedSize(true);
        setUpGallery();
        return rootView;
    }

    private void findViews(View rootview){

        mGalleryRv = (RecyclerView) rootview.findViewById(R.id.rv_gallery);
        mProgBarGallery = (ProgressBar) rootview.findViewById(R.id.pb_gallery);
        mTextViewError = (TextView) rootview.findViewById(R.id.tv_error_gallery);
    }

    private void setUpGallery(){
        getLoaderManager().initLoader(GalleryLoader.GALLERY_LOADER_ID,null,this);
        LoaderManager lm = getLoaderManager();
        Loader<Gallery[]> galLoader = lm.getLoader(GalleryLoader.GALLERY_LOADER_ID);
        if (galLoader == null){
            lm.initLoader(GalleryLoader.GALLERY_LOADER_ID,null,this);
        }else{
            lm.restartLoader(GalleryLoader.GALLERY_LOADER_ID,null,this).forceLoad();
        }
    }


    @Override
    public Loader<Gallery[]> onCreateLoader(int id, Bundle args) {
        mProgBarGallery.setVisibility(View.VISIBLE);
        return new GalleryLoader(mContext);
    }

    @Override
    public void onLoadFinished(Loader<Gallery[]> loader, Gallery[] gallery) {
        mProgBarGallery.setVisibility(View.GONE);
        if (gallery == null){
            mTextViewError.setVisibility(View.VISIBLE);
            mGalleryRv.setVisibility(View.INVISIBLE);
        }else if (gallery.length == 0){

            //There was no venues
        }else{
            mTextViewError.setVisibility(View.GONE);
            mGalleryRv.setVisibility(View.VISIBLE);
//            mVenueDatapter = new VenueDataAdapter(venues,mContext);
//            mGalleryRv.setAdapter(mVenueDatapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<Gallery[]> loader) {
        //Do nothing
    }


}
