package com.alexfanning.artwaves.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alexfanning.artwaves.R;
import com.alexfanning.artwaves.activities.GalleryDetailActivity;
import com.alexfanning.artwaves.activities.MainActivity;
import com.alexfanning.artwaves.galleryitems.Gallery;
import com.alexfanning.artwaves.galleryitems.GalleryDataAdapter;
import com.alexfanning.artwaves.galleryitems.GalleryLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment implements LoaderManager.LoaderCallbacks<Gallery[]>,
                                                            GalleryDataAdapter.ListItemClickListener {
    public GalleryFragment(){}

    private Context mContext;
    private RecyclerView mRvGallery;
    private GalleryDataAdapter mGalAdapter;
    private TextView mTvError;
    private ProgressBar mPb;
    private static final int NUM_ROWS_VERTICAL = 2;
    private static final int NUM_ROWS_HORIZONTAL = 3;
    private static final String POS_KEY = "key";
    StaggeredGridLayoutManager mGridManager;
    private static int sPos = -1;

    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_gallery, container, false);
        findViews(rootview);
        int numColumns = (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) ? NUM_ROWS_VERTICAL : NUM_ROWS_HORIZONTAL;
        mGridManager = new StaggeredGridLayoutManager(numColumns,StaggeredGridLayoutManager.VERTICAL);
        mGridManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        mRvGallery.setLayoutManager(mGridManager);
        mRvGallery.setHasFixedSize(false);
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
            getView().setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorBackground));
        }else{
            mTvError.setVisibility(View.GONE);
            mRvGallery.setVisibility(View.VISIBLE);
            mGalAdapter = new GalleryDataAdapter(galArray,mContext,this);
            mRvGallery.setAdapter(mGalAdapter);
            getView().setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorBlack));
            restoreGridPosition();
        }
    }

    @Override
    public void onLoaderReset(Loader<Gallery[]> loader) {

    }

    @Override
    public void onListItemClick(Gallery g) {
        Intent i = new Intent(mContext, GalleryDetailActivity.class);
        i.putExtra(mContext.getString(R.string.gal_key),g);
        startActivity(i);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray(POS_KEY,mGridManager.findFirstCompletelyVisibleItemPositions(null));
    }

    private void restoreGridPosition(){
        if (sPos != -1){
            mGridManager.scrollToPosition(sPos);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null){
            int[] pos2 = savedInstanceState.getIntArray(POS_KEY);
            sPos= pos2[0];
            restoreGridPosition();
        }
    }


}
