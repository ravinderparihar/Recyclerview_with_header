package com.bhs.myapplication;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhs.myapplication.library.Section;
import com.bhs.myapplication.library.SectionParameters;

import java.util.List;

public final class ContactsSection extends Section {

    private final String title;
    private final List<Contact> list;
    private final ClickListener clickListener;

    ContactsSection(@NonNull final String title, @NonNull final List<Contact> list,
                    @NonNull final ClickListener clickListener) {

        super(SectionParameters.builder()
                .itemResourceId(R.layout.section_ex1_item)
                .headerResourceId(R.layout.section_ex1_header)
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

        final Contact contact = list.get(position);

        itemHolder.tvItem.setText(contact.name);
        itemHolder.imgItem.setImageResource(contact.profileImage);

        itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       clickListener.onItemRootViewClicked(ContactsSection.this, itemHolder.getAdapterPosition());
                                                   }
                                               }
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
    }

    interface ClickListener {

        void onItemRootViewClicked(@NonNull final ContactsSection section, final int itemAdapterPosition);
    }
}
