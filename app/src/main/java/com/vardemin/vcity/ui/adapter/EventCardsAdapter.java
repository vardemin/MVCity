package com.vardemin.vcity.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vardemin.vcity.R;
import com.vardemin.vcity.data.models.scheme.EventScheme;
import com.vardemin.vcity.data.models.scheme.PhotoScheme;

import java.util.List;

/**
 * Created by vard on 12.11.17.
 */

public class EventCardsAdapter extends RecyclerView.Adapter<CardHolder> {

    private List<EventScheme> schemes;
    private View.OnClickListener listener;

    public EventCardsAdapter(List<EventScheme> schemes, View.OnClickListener listener) {
        this.schemes = schemes;
        this.listener = listener;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_card_image, parent, false);

        if (listener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view);
                }
            });
        }

        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        if (schemes.get(position).getPhotos()==null) return;
        PhotoScheme photo = schemes.get(position).getPhotos().get(0);
        if ( photo!=null)
            holder.loadData(photo.getPath());
    }

    @Override
    public int getItemCount() {
        return schemes.size();
    }

}
