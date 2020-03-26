package com.andrei.traveljournal.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.andrei.traveljournal.R;
import com.andrei.traveljournal.models.TripModel;

public class TripsViewHolder extends RecyclerView.ViewHolder {
    private TextView mTripName;
    private TextView mDestination;
    public TripsViewHolder(@NonNull View container) {
        super(container);
        mTripName = container.findViewById(R.id.trip_name);
        mDestination = container.findViewById(R.id.destination);
    }
    public void bind(TripModel trip) {
        mTripName.setText(trip.getTripName());
        mDestination.setText(trip.getDestination());
    }
}
