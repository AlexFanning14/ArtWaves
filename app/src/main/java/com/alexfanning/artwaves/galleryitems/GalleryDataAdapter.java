package com.alexfanning.artwaves.galleryitems;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexfanning.artwaves.R;
import com.alexfanning.artwaves.venueitems.Venue;
import com.ms.square.android.expandabletextview.ExpandableTextView;

/**
 * Created by alex.fanning on 24/08/2017.
 */

public class GalleryDataAdapter extends RecyclerView.Adapter<com.alexfanning.artwaves.galleryitems.GalleryDataAdapter.GalleryViewHolder> {
    private static final String TAG = GalleryDataAdapter.class.getSimpleName();
    private Gallery[] mGalleryItems;
    private static int sViewHolderCount;
    private int mNumberItems;
    private Context context;


    public GalleryDataAdapter(Gallery[] gals, Context c){
        mNumberItems = gals.length;
        this.mGalleryItems = gals;
        this.context = c;
        sViewHolderCount = 0;
    }

    @Override
    public GalleryDataAdapter.GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutIdForGridItem = R.layout.gallery_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForGridItem, parent, false);
        GalleryDataAdapter.GalleryViewHolder venueViewHolder = new GalleryDataAdapter.GalleryViewHolder(view);
        sViewHolderCount++;
        return venueViewHolder;
    }

    @Override
    public void onBindViewHolder(GalleryDataAdapter.GalleryViewHolder holder, int position) {
        holder.bind(mGalleryItems[position]);
    }


    @Override
    public int getItemCount() {
        return mGalleryItems.length;
    }

    class GalleryViewHolder extends RecyclerView.ViewHolder {
        TextView tvImgTitle = null;
        ImageView imgGallery = null;
        TextView tvGalleryArtist = null;
        TextView tvGalleryDescript = null;

        public GalleryViewHolder(View itemView){
            super(itemView);
            tvImgTitle = (TextView) itemView.findViewById(R.id.gallery_title);
            tvGalleryArtist = (TextView) itemView.findViewById(R.id.gallery_artist);
            tvGalleryDescript = (TextView) itemView.findViewById(R.id.gallery_descript);
            imgGallery = (ImageView) itemView.findViewById(R.id.gallery_img);
        }

        void bind(Gallery g){
            tvImgTitle.setText(g.getTitle());
            imgGallery.setImageDrawable(g.getImg());
            tvGalleryArtist.setText(g.getArtist());
            tvGalleryDescript.setText(g.getDescript());
        }

    }
}
