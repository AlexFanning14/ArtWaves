package com.alexfanning.artwaves.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexfanning.artwaves.R;
import com.alexfanning.artwaves.galleryitems.Gallery;
import com.alexfanning.artwaves.galleryitems.GalleryPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
**/
public class GalleryFragment extends Fragment {
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        Gallery[] items = new Gallery[]{
                new Gallery("Picture Title",mContext.getDrawable(R.drawable.sample_img),"John Doe","Sample Descritpionj")
        };

        GalleryPagerAdapter galleryPagerAdapter = new GalleryPagerAdapter(mContext,items);

        ViewPager mViewPager = (ViewPager)rootView.findViewById(R.id.pager);
        mViewPager.setAdapter(galleryPagerAdapter);

        return rootView;
    }



}
