package com.bhs.myapplication.example4;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhs.myapplication.R;

class ItemViewHolder extends RecyclerView.ViewHolder {

    final View rootView;
    final ImageView imgItem;
    final TextView tvItem;

    ItemViewHolder(@NonNull View view) {
        super(view);

        rootView = view;
        imgItem = view.findViewById(R.id.imgItem);
        tvItem = view.findViewById(R.id.tvItem);
    }
}
