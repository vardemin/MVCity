package com.vardemin.vcity.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vardemin.vcity.R;
import com.vardemin.vcity.data.models.scheme.EventScheme;
import com.vardemin.vcity.data.models.scheme.PhotoScheme;

import java.util.List;

/**
 * Created by vard on 12.11.17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {

    private List<EventScheme> schemes;
    private View.OnClickListener listener;

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
        PhotoScheme photo = schemes.get(position).getPhotos().get(0);
        if (photo!=null)
            holder.loadData(photo.getPath());
    }

    @Override
    public int getItemCount() {
        return schemes.size();
    }

    class CardHolder extends RecyclerView.ViewHolder {
        private ImageView imgCard;

        public CardHolder(View itemView) {
            super(itemView);
            imgCard = itemView.findViewById(R.id.image_card);
        }

        public void loadData(String url){
            Picasso.with(imgCard.getContext()).load(url).into(imgCard);
        }
    }

    interface CardItemListener {
        void onCard(int position);
    }
}
