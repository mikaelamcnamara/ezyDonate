package com.example.ezydonate;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class EventFragment extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<Event> lstEvent;
    private Animation animationUp, animationDown;
    private RecyclerViewAdapter adapter;
    private DatabaseReference mDatabase;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_event, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(),lstEvent);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;









    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*List for adding cards */
        lstEvent = new ArrayList<>();
        lstEvent.add(new Event("MARATHON SPRINT", "Be entitled to run a marathon.", "17 Waragael Town", "04/05/19", "9:30AM", R.drawable.app_icon));
        lstEvent.add(new Event("VEGEtarian SPRINT", "Be entitled to run a marathon.", "17 Waragael Town", "04/05/19", "9:30AM", R.drawable.app_icon));



    /*    public void makeEvent(View view) {

        Intent makeEvent = new Intent(getActivity(), EventActivity.class);
        startActivity(makeEvent);

    }*/
    }



}


