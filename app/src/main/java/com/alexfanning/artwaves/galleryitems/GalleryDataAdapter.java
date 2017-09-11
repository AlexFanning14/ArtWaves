package com.alexfanning.artwaves.galleryitems;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alexfanning.artwaves.R;
import com.squareup.picasso.Picasso;

/**
 * Created by User on 11-Sep-17.
 */

public class GalleryDataAdapter extends RecyclerView.Adapter<GalleryDataAdapter.GalleryViewHolder> {


    private static final String TAG = GalleryDataAdapter.class.getSimpleName();
    private Gallery[] mGalleries;
    private Context context;

    public GalleryDataAdapter(Gallery[] galleries, int mNumberItems, Context context) {
        this.mGalleries = galleries;
        this.context = context;
    }


    @Override
    public GalleryDataAdapter.GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutIdForGridItem = R.layout.gallery_grid_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForGridItem, parent, false);
        GalleryViewHolder galViewHolder = new GalleryViewHolder(view);
        return galViewHolder;
    }

    @Override
    public void onBindViewHolder(GalleryDataAdapter.GalleryViewHolder holder, int position) {
        holder.bind(mGalleries[position]);
    }

    @Override
    public int getItemCount() {
        return mGalleries.length;
    }

    class GalleryViewHolder extends RecyclerView.ViewHolder{

        ImageView mImgView = null;


        public GalleryViewHolder(View itemView) {
            super(itemView);
            mImgView = (ImageView)itemView.findViewById(R.id.img_gallery_item);
        }

        void bind(Gallery g){
            Picasso.with(context).load(g.getImgSrc()).into(mImgView);
        }

    }


}
