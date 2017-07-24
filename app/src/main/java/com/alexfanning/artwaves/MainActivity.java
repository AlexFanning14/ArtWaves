package com.alexfanning.artwaves;

import android.app.Fragment;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerTitle = getTitle().toString();
        mTitle = getTitle().toString();
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.drawer_fragment_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        setUpToolbar();

        NavDrawerItem[] navDrawerItems = new NavDrawerItem[4];

        navDrawerItems[0] = new NavDrawerItem(R.drawable.ic_art_icon,getString(R.string.nav_home));
        navDrawerItems[1] = new NavDrawerItem(R.drawable.ic_art_icon,getString(R.string.nav_aims));
        navDrawerItems[2] = new NavDrawerItem(R.drawable.ic_art_icon,getString(R.string.nav_arttrail));
        navDrawerItems[3] = new NavDrawerItem(R.drawable.ic_art_icon,getString(R.string.nav_submission));

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.drawer_list_item,navDrawerItems);
        View header = getLayoutInflater().inflate(R.layout.drawer_header,null);
        mDrawerList.addHeaderView(header);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        setUpDrawerToggle();
        new DrawerItemClickListener().selectItem(0);
    }



    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

        private void selectItem(int position){
            android.support.v4.app.Fragment fragment = null;



            // TODO remove harcoded values
            switch (position){
                case 0:
                    fragment = new HomeFragment();
                    break;
                default:
                    break;
            }

            if (fragment != null){
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();

                mDrawerList.setItemChecked(position,true);
                mDrawerList.setSelection(position);
                setTitle(mNavigationDrawerItemTitles[position]);


                mDrawerLayout.closeDrawer(mDrawerList);
            }else{
                Log.e(TAG, "error creating fragemnt");
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name,R.string.app_name);
        mDrawerToggle.syncState();
    }

}
