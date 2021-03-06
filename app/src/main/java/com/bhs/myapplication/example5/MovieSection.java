package com.bhs.myapplication.example5;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.bhs.myapplication.library.Section;
import com.bhs.myapplication.library.SectionParameters;
import com.bhs.myapplication.R;

final class MovieSection extends Section {

    private final String title;
    private final List<Movie> list;
    private final ClickListener clickListener;

    MovieSection(@NonNull final String title, @NonNull final List<Movie> list,
                 @NonNull final ClickListener clickListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.section_ex5_item)
                .headerResourceId(R.layout.section_ex5_header)
                .build());

        this.title = title;
        this.list = list;
        this.clickListener = clickListener;
    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(final View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;

        final Movie movie = list.get(position);

        itemHolder.tvItem.setText(movie.name);
        itemHolder.tvSubItem.setText(movie.category);

        itemHolder.rootView.setOnClickListener(v ->
                clickListener.onItemRootViewClicked(this, itemHolder.getAdapterPosition())
        );
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(final View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(final RecyclerView.ViewHolder holder) {
        final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

        headerHolder.tvTitle.setText(title);

        headerHolder.btnMore.setOnClickListener(v ->
                clickListener.onHeaderMoreButtonClicked(this, headerHolder.getAdapterPosition())
        );
    }

    interface ClickListener {

        void onHeaderMoreButtonClicked(@NonNull final MovieSection section, int itemAdapterPosition);

        void onItemRootViewClicked(@NonNull final MovieSection section, final int itemAdapterPosition);
    }
}
