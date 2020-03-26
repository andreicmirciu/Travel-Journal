package com.andrei.traveljournal.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andrei.traveljournal.R;
import com.andrei.traveljournal.models.TripModel;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.ViewHolder> {

    // declare a list of trips
    private ArrayList<TripModel> mTripList;

    public interface OnItemClickListener {
        public void onItemClicked(int position);
    }

    public interface OnItemLongClickListener {
        public boolean onItemLongClicked(int position);
    }

    private Fragment mFragment;

    public TripListAdapter(ArrayList<TripModel> tripList) {
        this.mTripList = tripList;
    }

    public TripListAdapter(Fragment fragment) {
        this.mFragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //Glide.with(mContext).asBitmap().load(mImages.get(i)).into(viewHolder.image);
        viewHolder.tripName.setText(mTripList.get(i).getTripName());
        viewHolder.destination.setText(mTripList.get(i).getDestination());
        BitmapDrawable drawable = (BitmapDrawable) mTripList.get(i).getPicture().getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        viewHolder.image.setImageBitmap(bitmap);
        viewHolder.rating.setText(mTripList.get(i).getPrice() + "/"
                + mTripList.get(i).getRating());

        viewHolder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        viewHolder.v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return true;
            }
        });

    }



    @Override
    public int getItemCount() {
        if (mTripList == null) {
            return 0;
        }
        return mTripList.size();
    }

    public void addItem(TripModel trip) {
        if (mTripList == null) {
            mTripList = new ArrayList<>();
        }
        mTripList.add(trip);
        //notifyDataSetChanged();
        notifyItemInserted(mTripList.size() - 1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View v;

        ImageView image;
        TextView tripName;
        TextView destination;
        TextView rating;
        LinearLayout parentLayout;
        TripListAdapter adapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.v = itemView;
            image = itemView.findViewById(R.id.trip_photo);
            tripName = itemView.findViewById(R.id.trip_name);
            destination = itemView.findViewById(R.id.destination);
            rating = itemView.findViewById(R.id.rating_bar);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }


    }

}
