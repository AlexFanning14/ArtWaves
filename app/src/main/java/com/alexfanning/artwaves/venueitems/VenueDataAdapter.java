package com.alexfanning.artwaves.venueitems;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alexfanning.artwaves.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.Locale;

/**
 * Created by alex.fanning on 24/07/2017.
 */

public class VenueDataAdapter extends RecyclerView.Adapter<VenueDataAdapter.VenueViewHolder> {
    private static final String TAG = VenueDataAdapter.class.getSimpleName();
    private Venue[] venues;
    private static int sViewHolderCount;
    private int mNumberItems;
    private Context context;

    private static final String GEO_STR = "geo:";

    public VenueDataAdapter(Venue[] venues, Context c){
        mNumberItems = venues.length;
        this.venues = venues;
        this.context = c;
        sViewHolderCount = 0;
    }

    @Override
    public VenueDataAdapter.VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutIdForGridItem = R.layout.venue_grid_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForGridItem, parent, false);
        VenueViewHolder venueViewHolder = new VenueViewHolder(view);
        sViewHolderCount++;
        return venueViewHolder;
    }

    @Override
    public void onBindViewHolder(VenueViewHolder holder, int position) {
        holder.bind(venues[position]);
    }

    @Override
    public int getItemCount() {
        return venues.length;
    }

    class VenueViewHolder extends RecyclerView.ViewHolder {
        ExpandableTextView tvDescript = null;
        TextView tvVenueName = null;
        TextView tvLocation = null;
        Button btnFind = null;
        TextView tvDate = null;

        public VenueViewHolder(View itemView){
            super(itemView);
            tvDescript = (ExpandableTextView) itemView.findViewById(R.id.expand_text_view);
            tvVenueName = (TextView) itemView.findViewById(R.id.tv_venue_name);
            tvLocation = (TextView) itemView.findViewById(R.id.tv_location);
            tvDate = (TextView) itemView.findViewById(R.id.tv_venue_date);
            btnFind = (Button) itemView.findViewById(R.id.btn_find_location);

            View.OnClickListener btnFindList = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayLocation(v,getAdapterPosition());
                }
            } ;
            btnFind.setOnClickListener(btnFindList);


        }

        private void displayLocation(View v, int pos){
            Venue ven = venues[pos];
            String coordinates[] = ven.getCoordinates();
            final String INTENT_STR = "geo:" + coordinates[0] + "," + coordinates[1] + "?q=" + coordinates[0] + "," + coordinates[1] + "(" + ven.getName() + ")";

            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(INTENT_STR));

            if (intent.resolveActivity(context.getPackageManager()) != null){
                context.startActivity(intent);
            }

        }

        void bind(Venue v){
            tvDescript.setText(v.getDescription());
            tvVenueName.setText(v.getName());
            tvLocation.setText(v.getLocation());
            tvDate.setText(v.getDate());
        }

    }
}
