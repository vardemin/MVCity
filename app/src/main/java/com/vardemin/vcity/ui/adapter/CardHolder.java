package com.vardemin.vcity.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vardemin.vcity.R;

/**
 * Created by vard on 14.11.17.
 */

public class CardHolder extends RecyclerView.ViewHolder {

    private ImageView imgCard;

    public CardHolder(View itemView) {
        super(itemView);
        imgCard = itemView.findViewById(R.id.image_card);
    }

    public void loadData(String url){
        Picasso.with(imgCard.getContext()).load(url).into(imgCard);
    }

}
