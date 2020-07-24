package com.bhs.myapplication.example6;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhs.myapplication.library.SectionAdapter;

import com.bhs.myapplication.library.SectionedRecyclerViewAdapter;
import com.bhs.myapplication.R;
import com.bhs.myapplication.info.SectionInfoFactory;
import com.bhs.myapplication.info.SectionItemInfoDialog;
import com.bhs.myapplication.info.SectionItemInfoFactory;

public class Example6Fragment extends Activity implements ExpandableMovieSection.ClickListener {

    private static final String DIALOG_TAG = "SectionItemInfoDialogTag";

    private SectionedRecyclerViewAdapter sectionedAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionedAdapter = new SectionedRecyclerViewAdapter();

        final LoadMoviesUseCase loadMoviesUseCase = new LoadMoviesUseCase();
        sectionedAdapter.addSection(new ExpandableMovieSection(getString(R.string.top_rated_movies_topic),
                loadMoviesUseCase.execute(this, R.array.top_rated_movies), this));
        sectionedAdapter.addSection(new ExpandableMovieSection(getString(R.string.most_popular_movies_topic),
                loadMoviesUseCase.execute(this, R.array.most_popular_movies), this));

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);

        final GridLayoutManager glm = new GridLayoutManager(this, 2);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(final int position) {
                if (sectionedAdapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
                    return 2;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(sectionedAdapter);


    }

    @Override
    public void onHeaderRootViewClicked(@NonNull final ExpandableMovieSection section) {
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
    public void onItemRootViewClicked(@NonNull final ExpandableMovieSection section, final int itemAdapterPosition) {
        final SectionItemInfoDialog dialog = SectionItemInfoDialog.getInstance(
                SectionItemInfoFactory.create(itemAdapterPosition, sectionedAdapter),
                SectionInfoFactory.create(section, sectionedAdapter.getAdapterForSection(section))
        );
//        dialog.show(getParentFragmentManager(), DIALOG_TAG);
    }
}
