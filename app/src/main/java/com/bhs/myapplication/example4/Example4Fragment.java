package com.bhs.myapplication.example4;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import com.bhs.myapplication.R;
import com.bhs.myapplication.info.SectionInfoFactory;
import com.bhs.myapplication.info.SectionItemInfoDialog;
import com.bhs.myapplication.info.SectionItemInfoFactory;
import com.bhs.myapplication.library.SectionAdapter;
import com.bhs.myapplication.library.SectionedRecyclerViewAdapter;

public class Example4Fragment extends Activity implements ExpandableContactsSection.ClickListener {

    private static final String DIALOG_TAG = "SectionItemInfoDialogTag";

    private SectionedRecyclerViewAdapter sectionedAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionedAdapter = new SectionedRecyclerViewAdapter();

        final Map<String, List<Contact>> contactsMap = new LoadContactsUseCase().execute(this);
        for (final Map.Entry<String, List<Contact>> entry : contactsMap.entrySet()) {
            if (entry.getValue().size() > 0) {
                sectionedAdapter.addSection(new ExpandableContactsSection(entry.getKey(), entry.getValue(), this));
            }
        }

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(sectionedAdapter);


    }

    @Override
    public void onHeaderRootViewClicked(@NonNull final ExpandableContactsSection section) {
        final SectionAdapter sectionAdapter = sectionedAdapter.getAdapterForSection(section);

        // store info of current section state before changing its state
        final boolean wasExpanded = section.isExpanded();
        final int previousItemsTotal = section.getContentItemsTotal();

        section.setExpanded(!wasExpanded);
        sectionAdapter.notifyHeaderChanged();

        if (wasExpanded) {
            sectionAdapter.notifyItemRangeRemoved(0, previousItemsTotal);
        } else {
            sectionAdapter.notifyAllItemsInserted();
        }
    }

    @Override
    public void onItemRootViewClicked(@NonNull final ExpandableContactsSection section, final int itemAdapterPosition) {
        final SectionItemInfoDialog dialog = SectionItemInfoDialog.getInstance(
                SectionItemInfoFactory.create(itemAdapterPosition, sectionedAdapter),
                SectionInfoFactory.create(section, sectionedAdapter.getAdapterForSection(section))
        );
//        dialog.show(getParentFragmentManager(), DIALOG_TAG);
    }
}
