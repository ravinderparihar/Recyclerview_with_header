package com.bhs.myapplication.example7;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhs.myapplication.R;

class HeaderViewHolder extends RecyclerView.ViewHolder {

    final TextView tvTitle;

    HeaderViewHolder(@NonNull final View view) {
        super(view);

        tvTitle = view.findViewById(R.id.tvTitle);
    }
}
