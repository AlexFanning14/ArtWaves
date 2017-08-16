package com.alexfanning.artwaves.activities;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alexfanning.artwaves.R;
import com.alexfanning.artwaves.fragments.AimsFragment;
import com.alexfanning.artwaves.fragments.ArttrailFragment;
import com.alexfanning.artwaves.fragments.HomeFragment;
import com.alexfanning.artwaves.fragments.SubmissionFragment;
import com.alexfanning.artwaves.navdrawer.DrawerItemCustomAdapter;
import com.alexfanning.artwaves.navdrawer.NavDrawerItem;

public class MainActivity extends AppCompatActivity {
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private String mDrawerTitle;
    private String mTitle;
    ActionBarDrawerToggle mDrawerToggle;
    private static int sNavPosition = 0;
    private static final String NAV_POSITION_KEY = "navKey";
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerTitle = getTitle().toString();
        mTitle = getTitle().toString();
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.drawer_fragment_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        setUpToolbar();
        NavDrawerItem[] navDrawerItems = new NavDrawerItem[4];

        navDrawerItems[0] = new NavDrawerItem(getString(R.string.nav_home));
        navDrawerItems[1] = new NavDrawerItem(getString(R.string.nav_aims));
        navDrawerItems[2] = new NavDrawerItem(getString(R.string.nav_arttrail));
        navDrawerItems[3] = new NavDrawerItem(getString(R.string.nav_submission));

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.drawer_list_item,navDrawerItems);
        View header = getLayoutInflater().inflate(R.layout.drawer_header,null);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.addHeaderView(header);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        setUpDrawerToggle();
        if (savedInstanceState != null && savedInstanceState.containsKey(NAV_POSITION_KEY)){
            sNavPosition = savedInstanceState.getInt(NAV_POSITION_KEY);
        }
        new DrawerItemClickListener().selectItem(sNavPosition);
    }

    private void setUpDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name,R.string.app_name){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        };
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mDrawerToggle.setDrawerIndicatorEnabled(true);

        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position - 1);

        }

        private void selectItem(int position){
            android.support.v4.app.Fragment fragment = null;

            switch (position){
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new AimsFragment();
                    break;
                case 2:
                    fragment = new ArttrailFragment();
                    break;
                case 3:
                    fragment = new SubmissionFragment();
                    break;
                default:
                    break;
            }

            sNavPosition = position;
            if (fragment != null){
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();

                mDrawerList.setItemChecked(position,true);
                mDrawerList.setSelection(position);
                String title = mNavigationDrawerItemTitles[position];
                if (title.equals(getString(R.string.nav_submission))){
                    setTitle(getString(R.string.submission_header));
                }else{
                    setTitle(title);
                }
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title.toString();
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void setUpToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(NAV_POSITION_KEY,sNavPosition);
    }
}
