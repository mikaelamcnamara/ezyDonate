package com.example.ezydonate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BookingFragmentAdmin extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.admin_bookingview, container, false);
    }


    public void makeBooking(View view) {

        Intent makeEvent = new Intent(getActivity(), BookingMakeActivity.class);
        startActivity(makeEvent);

    }

    public void cancelBooking(View view) {

        Intent makeEvent = new Intent(getActivity(), BookingCancelActivity.class);
        startActivity(makeEvent);

    }
}
