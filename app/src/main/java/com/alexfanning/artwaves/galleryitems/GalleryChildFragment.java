package com.alexfanning.artwaves.galleryitems;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexfanning.artwaves.R;

/**

 */
public class GalleryChildFragment extends Fragment {
    private Gallery g;
    private static final String GALLERY_KEY = "galkey";
    public static GalleryChildFragment newInstance(Gallery g){
        GalleryChildFragment frag = new GalleryChildFragment();
        Bundle args = new Bundle();
        args.putParcelable(GALLERY_KEY,g);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        g = getArguments().getParcelable(GALLERY_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.fragment_gallery_child,container,false);
        TextView tvImgTitle = (TextView) itemView.findViewById(R.id.gallery_title);
        TextView tvGalleryArtist = (TextView) itemView.findViewById(R.id.gallery_artist);
        TextView tvGalleryDescript = (TextView) itemView.findViewById(R.id.gallery_descript);
        ImageView imgGallery = (ImageView) itemView.findViewById(R.id.gallery_img);

        tvImgTitle.setText(g.getTitle());
        imgGallery.setImageDrawable(g.getImg());
        tvGalleryArtist.setText(g.getArtist());
        tvGalleryDescript.setText(g.getDescript());
        return itemView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }




}
