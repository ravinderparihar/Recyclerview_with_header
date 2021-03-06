package com.bhs.myapplication.example9;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhs.myapplication.R;

class PortfolioItemViewHolder extends RecyclerView.ViewHolder {

    final View rootView;
    final TextView tvCode;
    final TextView tvHoldingQty;
    final TextView tvHoldingPrice;
    final TextView tvPrice;
    final TextView tvDelta;

    PortfolioItemViewHolder(@NonNull final View view) {
        super(view);

        rootView = view;
        tvCode = view.findViewById(R.id.tvCode);
        tvHoldingQty = view.findViewById(R.id.tvHoldingQty);
        tvHoldingPrice = view.findViewById(R.id.tvHoldingPrice);
        tvPrice = view.findViewById(R.id.tvPrice);
        tvDelta = view.findViewById(R.id.tvDelta);
    }
}
