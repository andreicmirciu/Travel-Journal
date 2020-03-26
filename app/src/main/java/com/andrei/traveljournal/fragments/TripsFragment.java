package com.andrei.traveljournal.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrei.traveljournal.R;

public class TripsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trips, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle fragmentData = getArguments();
        if (fragmentData != null) {
            Log.d("TripsFragment", fragmentData.getString("foo"));
        }
    }
}
