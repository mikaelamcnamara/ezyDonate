package com.example.ezydonate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<EventHistory> lstHist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_event, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.recyclerviewhistory_id);
        RecyclerViewAdapterHistory recyclerAdapter = new RecyclerViewAdapterHistory(getContext(),lstHist);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstHist = new ArrayList<>();
        lstHist.add(new EventHistory("The Vegetarian", "yo", "hello","fdksdfk","ninehirty", "hi"));
        lstHist.add(new EventHistory("The Vegetarianss", "yo", "hello","fdksdfk","ninehirty","hi"));
        lstHist.add(new EventHistory("The Vegetarian", "yo", "hello","fdksdfk","ninehirty", "hi"));
        lstHist.add(new EventHistory("The Vegetarianss", "yo", "hello","fdksdfk","ninehirty","hi"));
    }

}