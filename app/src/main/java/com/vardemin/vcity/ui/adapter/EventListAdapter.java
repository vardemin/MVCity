package com.vardemin.vcity.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vardemin.vcity.R;
import com.vardemin.vcity.data.models.scheme.EventScheme;

import java.util.List;

import butterknife.BindView;

/**
 * Created by SimUser on 24.10.2017.
 */

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {
    private List<EventScheme> events;
    private ChooseEventListener listener;
    public EventListAdapter(List<EventScheme> events, ChooseEventListener listener) {
        this.events = events;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event,parent, false);
        ViewHolder holder = new ViewHolder(view);
        int pos = holder.getAdapterPosition();
        if (pos!=RecyclerView.NO_POSITION) {
            listener.onChoose(events.get(pos));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EventScheme scheme = events.get(position);
        if(scheme==null) return;
        if(scheme.getPhotos()!=null && scheme.getPhotos().get(0)!=null)
            Picasso.with(holder.img.getContext()).load(scheme.getPhotos().get(0).getPath()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ViewHolder(View view) {
            super(view);
        }
    }

    interface ChooseEventListener {
        void onChoose(EventScheme event);
    }
}
