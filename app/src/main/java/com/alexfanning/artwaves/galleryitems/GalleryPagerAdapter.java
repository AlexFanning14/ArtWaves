package com.alexfanning.artwaves.galleryitems;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alexfanning.artwaves.R;

import org.w3c.dom.Text;

/**
 * Created by User on 22-Aug-17.
 */

public class GalleryPagerAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    Gallery[] mGalleryItems;

    public GalleryPagerAdapter(Context context,Gallery[] galleryItems){
        mContext = context;
        mLayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mGalleryItems = galleryItems;
    }

    @Override
    public int getCount() {
        return mGalleryItems.length;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((FrameLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.gallery_item,container,false);
        TextView tvTitle = (TextView) itemView.findViewById(R.id.gallery_title);
        TextView tvArtist = (TextView) itemView.findViewById(R.id.gallery_artist);
        TextView tvDescript = (TextView) itemView.findViewById(R.id.galler_descript);
        ImageView imgGalView = (ImageView) itemView.findViewById(R.id.gallery_img);
        Gallery galItem = mGalleryItems[position];

        tvTitle.setText(galItem.getTitle());
        tvArtist.setText(galItem.getArtist());
        tvDescript.setText(galItem.getDescript());
        imgGalView.setImageDrawable(galItem.getImg());

        container.addView(itemView);

        return itemView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout)object);
        super.destroyItem(container, position, object);
    }
}
