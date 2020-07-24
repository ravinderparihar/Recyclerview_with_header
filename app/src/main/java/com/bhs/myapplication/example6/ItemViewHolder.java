package com.bhs.myapplication.example6;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhs.myapplication.R;

final class ItemViewHolder extends RecyclerView.ViewHolder {

    final View rootView;
    final TextView tvItem;
    final TextView tvSubItem;

    ItemViewHolder(@NonNull final View view) {
        super(view);

        rootView = view;
        tvItem = view.findViewById(R.id.tvItem);
        tvSubItem = view.findViewById(R.id.tvSubItem);
    }
}
