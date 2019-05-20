package com.example.ezydonate;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {

    Context mContext;

    private static int currentPosition = 0;
    List<Event> mData;
    private List<Event> exampleList;
    private List<Event> exampleListFull;

    public RecyclerViewAdapter(Context mContext, List<Event> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.cardview_event, parent,false);
        final MyViewHolder vHolder = new MyViewHolder(v,mContext);


        return vHolder;




    }


    RecyclerViewAdapter(List<Event> exampleList) {
        this.exampleList = exampleList;
        exampleListFull = new ArrayList<>(exampleList);
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Event> filteredList = new ArrayList<>();

            if(constraint ==null||constraint.length()==0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Event item: exampleListFull) {
                    if(item.title.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exampleList.clear();
            exampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }

    };


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.event_title.setText(mData.get(position).getTitle());
        //holder.event_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.event_time.setText(mData.get(position).getTime());
        holder.event_description.setText(mData.get(position).getDescription());
        holder.event_date.setText(mData.get(position).getEventDate());
        holder.event_location.setText(mData.get(position).getLocation());


    }


    @Override
    public int getItemCount() {
        return mData.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView event_title;
        private TextView event_time;
        private TextView event_description;
        private TextView event_date;
        private TextView event_location;
        private ImageView event_thumbnail;
        private LinearLayout event;
        private Button btn;




        public MyViewHolder(View itemView, Context mContext) {
            super(itemView);
            event_thumbnail = itemView.findViewById(R.id.event_img_id);
            event_title =  itemView.findViewById(R.id.event_title_id);
            event_time =itemView.findViewById(R.id.event_time_id);
            event_date = itemView.findViewById(R.id.event_date_id);
            event_location=  itemView.findViewById(R.id.event_location_id);
            event_description = itemView.findViewById(R.id.event_description_id);
            event = itemView.findViewById(R.id.transition_container);
            btn = itemView.findViewById(R.id.event_more);
            btn.setOnClickListener(this);

        }
        boolean visible;
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View v) {
            TransitionManager.beginDelayedTransition(event);
            visible =! visible;
            event_description.setVisibility(visible ? View.VISIBLE : View.GONE);

        }
    }
}