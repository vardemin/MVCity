package com.vardemin.vcity.ui.adapter;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vardemin.vcity.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vard on 21.11.17.
 */

public class UriPhotosAdapter extends RecyclerView.Adapter<UriPhotosAdapter.PhotoHolder> {

    private List<Uri> photos;
    private Context context;
    private View.OnClickListener listener;

    public UriPhotosAdapter(List<Uri> photos, Context context, View.OnClickListener listener) {
        this.photos = photos;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_image,parent, false);
        if(listener!=null)
            view.setOnClickListener(listener);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        Uri uri = photos.get(position);

        if (uri!=null) {
            Picasso.with(context).load(uri).centerCrop().into(holder.img);
            holder.btnClose.setVisibility(View.VISIBLE);
            holder.btnAdd.setVisibility(View.GONE);
        }
        else {
            holder.img.setImageURI(null);
            holder.btnClose.setVisibility(View.GONE);
            holder.btnAdd.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class PhotoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.btn_add)
        ImageView btnAdd;
        @BindView(R.id.image_card)
        ImageView img;
        @BindView(R.id.btn_close)
        View btnClose;

        public PhotoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
