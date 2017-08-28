package com.alexfanning.artwaves.galleryitems;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexfanning.artwaves.R;

/**
 * Created by alex.fanning on 25/08/2017.
 */

public class CustomPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private Gallery[] mGalleryItems;

    public CustomPagerAdapter(FragmentManager fm, Gallery[] galleryItems){
        super(fm);
        mGalleryItems = galleryItems;
    }

    @Override
    public Fragment getItem(int position) {
        Gallery g = mGalleryItems[position];
        return GalleryChildFragment.newInstance(g);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return mGalleryItems.length;
    }

}
