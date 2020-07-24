package com.bhs.myapplication.example9;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhs.myapplication.R;

class PortfolioHeaderViewHolder extends RecyclerView.ViewHolder {

    final TextView tvHoldingsPriceTotal;

    PortfolioHeaderViewHolder(@NonNull final View view) {
        super(view);

        tvHoldingsPriceTotal = view.findViewById(R.id.tvHoldingsPriceTotal);
    }
}
