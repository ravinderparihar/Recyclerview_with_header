package com.bhs.myapplication;

import android.os.Bundle;

import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhs.myapplication.info.SectionInfoFactory;
import com.bhs.myapplication.info.SectionItemInfoDialog;
import com.bhs.myapplication.info.SectionItemInfoFactory;
import com.bhs.myapplication.library.SectionedRecyclerViewAdapter;


public class Example1Fragment extends FragmentActivity implements ContactsSection.ClickListener {

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
                sectionedAdapter.addSection(new ContactsSection(entry.getKey(), entry.getValue(), this));
            }
        }

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(sectionedAdapter);


    }

    @Override
    public void onItemRootViewClicked(@NonNull final ContactsSection section, final int itemAdapterPosition) {
//        final SectionItemInfoDialog dialog = SectionItemInfoDialog.getInstance(
//                SectionItemInfoFactory.create(itemAdapterPosition, sectionedAdapter),
//                SectionInfoFactory.create(section, sectionedAdapter.getAdapterForSection(section))
//        );
//        dialog.show(getParentFragmentManager(), DIALOG_TAG);
    }
}
