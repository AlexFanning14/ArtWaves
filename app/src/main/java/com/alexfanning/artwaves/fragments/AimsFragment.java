package com.alexfanning.artwaves.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexfanning.artwaves.R;


/**
 * A simple {@link Fragment} subclass.

 */
public class AimsFragment extends Fragment {
    private TextView mTvBulletPoints;
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_aims, container, false);
        findViews(rootView);
        setUpBulletPoints();
        return rootView;
    }

    private void findViews(View rootView){
        mTvBulletPoints = (TextView) rootView.findViewById(R.id.tv_bullet_points);
    }

    private void setUpBulletPoints(){
        String bulletPoint = mContext.getString(R.string.bullet_point);
        String aim1 = mContext.getString(R.string.aims_1);
        String aim2 = mContext.getString(R.string.aims_2);
        String aim3 = mContext.getString(R.string.aims_3);
        String newLineChar = mContext.getString(R.string.newline_char);
        String bulletPoints =  bulletPoint + aim1 + newLineChar + newLineChar + bulletPoint + aim2 + newLineChar + newLineChar + bulletPoint + aim3;

        mTvBulletPoints.setText(bulletPoints);


    }




}
