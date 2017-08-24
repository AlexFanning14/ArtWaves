package com.alexfanning.artwaves.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexfanning.artwaves.R;
import com.alexfanning.artwaves.galleryitems.Gallery;
import com.alexfanning.artwaves.galleryitems.GalleryDataAdapter;

/**
 * A simple {@link Fragment} subclass.
**/
public class GalleryFragment extends Fragment {
    private Context mContext;
    private RecyclerView mGalleryRv;
    private GalleryDataAdapter mGalAdapter;
    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        Gallery[] items = generateGalleryArray();
        mGalleryRv = (RecyclerView)rootView.findViewById(R.id.rv_galleries);
        mGalleryRv.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        mGalleryRv.setHasFixedSize(true);
        mGalAdapter = new GalleryDataAdapter(items,mContext);
        mGalleryRv.setAdapter(mGalAdapter);
        return rootView;
    }


    private Gallery[] generateGalleryArray(){
        return new Gallery[]{
                new Gallery("Picture Title",mContext.getDrawable(R.drawable.sample_img),"John Doe","Sample Descritpionj"),
                new Gallery("Picture Title",mContext.getDrawable(R.drawable.sample_img2),"John Doe","Sample Descritpionj"),
                new Gallery("Picture Title",mContext.getDrawable(R.drawable.sample_img3),"John Doe","Sample Descritpionj")
        };
    }


}
