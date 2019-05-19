package com.example.ezydonate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Event> mData;

    public RecyclerViewAdapter(Context mContext, List<Event> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.cardview_event, parent,false);
       MyViewHolder vHolder = new MyViewHolder(v);
       return vHolder;

    }

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



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView event_title;
        private TextView event_time;
        private TextView event_description;
        private TextView event_date;
        private TextView event_location;
        private ImageView event_thumbnail;



        public MyViewHolder(View itemView) {
            super(itemView);
            event_thumbnail = itemView.findViewById(R.id.event_img_id);
            event_title =  itemView.findViewById(R.id.event_title_id);
            event_time =itemView.findViewById(R.id.event_time_id);
            event_date = itemView.findViewById(R.id.event_date_id);
            event_location=  itemView.findViewById(R.id.event_location_id);
            event_description = itemView.findViewById(R.id.event_description_id);


        }
    }
}
