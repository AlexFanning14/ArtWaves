package com.alexfanning.artwaves.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexfanning.artwaves.R;
import com.alexfanning.artwaves.galleryitems.Gallery;
import com.squareup.picasso.Picasso;

public class GalleryDetailActivity extends AppCompatActivity {
    private Gallery g;
    private ImageView imgView;
    private TextView tvTitle;
    private TextView tvArtist;
    private TextView tvError;
    private static final String OLD_FORMAT = "?format=300w";
    private static final String NEW_FORMAT = "?format=1000w";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail);

        ActionBar ab = this.getSupportActionBar();
        if (ab!= null)
            ab.setDisplayHomeAsUpEnabled(true);

        findViews();

        Intent i = getIntent();
        if (i != null){
            g = i.getParcelableExtra(getString(R.string.gal_key));
        }
        if (isNetworkAvailable()){
            populateFields();
        }else{
            displayError();
        }


    }

    private void findViews(){
        imgView = (ImageView)findViewById(R.id.img_view_detail);
        tvTitle = (TextView) findViewById(R.id.tv_title_detail);
        tvArtist = (TextView) findViewById(R.id.tv_artist_detail);
        tvError = (TextView)findViewById(R.id.tv_network_detail);
    }

    private void populateFields(){
        tvError.setVisibility(View.GONE);
        tvTitle.setVisibility(View.VISIBLE);
        tvArtist.setVisibility(View.VISIBLE);
        imgView.setVisibility(View.VISIBLE);
        String newImgSrc = g.getImgSrc().replace(OLD_FORMAT,NEW_FORMAT);
        Picasso.with(this).load(newImgSrc).into(imgView);
        tvTitle.setText(g.getTitle());
        tvArtist.setText(g.getArtist());
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void displayError(){
        tvError.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.GONE);
        tvArtist.setVisibility(View.GONE);
        imgView.setVisibility(View.GONE);
    }



}
