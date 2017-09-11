package com.alexfanning.artwaves.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alexfanning.artwaves.R;
import com.alexfanning.artwaves.galleryitems.Gallery;
import com.alexfanning.artwaves.galleryitems.GalleryDataAdapter;
import com.alexfanning.artwaves.galleryitems.GalleryLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment implements LoaderManager.LoaderCallbacks<Gallery[]> {
    public GalleryFragment(){}

    private Context mContext;
    private RecyclerView mRvGallery;
    private GalleryDataAdapter mGalAdapter;
    private TextView mTvError;
    private ProgressBar mPb;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_gallery, container, false);
        findViews(rootview);

        mRvGallery.setLayoutManager(new GridLayoutManager(mContext,3));
        mRvGallery.setHasFixedSize(true);
        setUpLoader();
        return rootview;
    }

    private void findViews(View rootView){
        mRvGallery = (RecyclerView) rootView.findViewById(R.id.rv_gallery);
        mPb = (ProgressBar) rootView.findViewById(R.id.gal_progbar);
        mTvError = (TextView)rootView.findViewById(R.id.tv_error_gallery);
    }

    private void setUpLoader(){
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
        mPb.setVisibility(View.VISIBLE);
        mRvGallery.setVisibility(View.GONE);
        return new GalleryLoader(mContext);
    }

    @Override
    public void onLoadFinished(Loader<Gallery[]> loader, Gallery[] galArray) {
        mPb.setVisibility(View.GONE);
        if (galArray == null){
            mTvError.setVisibility(View.VISIBLE);
            mRvGallery.setVisibility(View.GONE);
        }else{
            mTvError.setVisibility(View.GONE);
            mRvGallery.setVisibility(View.VISIBLE);
            mGalAdapter = new GalleryDataAdapter(galArray,mContext);
            mRvGallery.setAdapter(mGalAdapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<Gallery[]> loader) {

    }
}
