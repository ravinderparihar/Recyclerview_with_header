package com.bhs.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

final class HeaderViewHolder extends RecyclerView.ViewHolder {

    final TextView tvTitle;

    HeaderViewHolder(@NonNull View view) {
        super(view);

        tvTitle = view.findViewById(R.id.tvTitle);
    }
}
