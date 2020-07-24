package com.bhs.myapplication.example3;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhs.myapplication.R;

final class FailedViewHolder extends RecyclerView.ViewHolder {

    final View rootView;

    FailedViewHolder(@NonNull final View itemView) {
        super(itemView);

        rootView = itemView.findViewById(R.id.rootView);
    }
}
