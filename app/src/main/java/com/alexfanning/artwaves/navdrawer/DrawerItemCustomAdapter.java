package com.alexfanning.artwaves.navdrawer;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexfanning.artwaves.R;

/**
 * Created by alex.fanning on 24/07/2017.
 */

public class DrawerItemCustomAdapter extends ArrayAdapter<NavDrawerItem> {

    Context mContext;
    int layoutResourceID;
    NavDrawerItem items[] = null;

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceID,NavDrawerItem[] items){
        super(mContext,layoutResourceID,items);
        this.mContext = mContext;
        this.layoutResourceID = layoutResourceID;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceID,parent,false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
        TextView textViewName = (TextView) listItem.findViewById(R.id.tv_drawer);

        NavDrawerItem item = items[position];

        imageViewIcon.setImageResource(item.icon);
        textViewName.setText(item.name);

        return listItem;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
