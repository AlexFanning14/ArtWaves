package com.alexfanning.artwaves.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.alexfanning.artwaves.R;
import com.alexfanning.artwaves.galleryitems.CustomPagerAdapter;
import com.alexfanning.artwaves.galleryitems.Gallery;

import java.util.Collections;

public class GalleryActivity extends AppCompatActivity {

    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        ActionBar ab = getSupportActionBar();
        if (ab!= null){
            ab.setDisplayHomeAsUpEnabled(true);
        }

        Gallery[] items = generateGalleryArray();

        mCustomPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(),items);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);

    }


    private Gallery[] generateGalleryArray(){
        return new Gallery[]{
                new Gallery("Picture Title",getDrawable(R.drawable.sample_img),"John Doe","Sample Descritpionj"),
                new Gallery("Picture Title",getDrawable(R.drawable.sample_img2),"John Doe","Sample Descritpionj"),
                new Gallery("Picture Title",getDrawable(R.drawable.sample_img3),"John Doe","Sample long descrion Sample long descrion Sample long descrion Sample long descrion Sample long descrion Sample long descrion Sample long descrion Sample long descrion")
        };
    }
}
