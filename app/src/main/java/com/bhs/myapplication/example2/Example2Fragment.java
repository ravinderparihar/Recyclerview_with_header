package com.bhs.myapplication.example2;

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

import com.bhs.myapplication.R;
import com.bhs.myapplication.info.SectionInfoFactory;
import com.bhs.myapplication.info.SectionItemInfoDialog;
import com.bhs.myapplication.info.SectionItemInfoFactory;
import com.bhs.myapplication.library.SectionedRecyclerViewAdapter;


public class Example2Fragment extends Activity implements NewsSection.ClickListener {

    private static final String DIALOG_TAG = "SectionItemInfoDialogTag";

    private SectionedRecyclerViewAdapter sectionedAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionedAdapter = new SectionedRecyclerViewAdapter();

        final LoadNewsUseCase loadNewsUseCase = new LoadNewsUseCase();

        sectionedAdapter.addSection(new NewsSection(
                this.getString(R.string.world_topic),
                loadNewsUseCase.execute(this, R.array.news_world,
                        R.drawable.ic_public_black_48dp),
                this
        ));
        sectionedAdapter.addSection(new NewsSection(
                this.getString(R.string.biz_topic),
                loadNewsUseCase.execute(this, R.array.news_biz,
                        R.drawable.ic_business_black_48dp),
                this
        ));
        sectionedAdapter.addSection(new NewsSection(
                this.getString(R.string.tech_topic),
                loadNewsUseCase.execute(this, R.array.news_tech,
                        R.drawable.ic_devices_other_black_48dp),
                this
        ));
        sectionedAdapter.addSection(new NewsSection(
                this.getString(R.string.sports_topic),
                loadNewsUseCase.execute(this, R.array.news_sports,
                        R.drawable.ic_directions_run_black_48dp),
                this
        ));

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(sectionedAdapter);


    }

    @Override
    public void onItemRootViewClicked(@NonNull final NewsSection section, final int itemAdapterPosition) {
        final SectionItemInfoDialog dialog = SectionItemInfoDialog.getInstance(
                SectionItemInfoFactory.create(itemAdapterPosition, sectionedAdapter),
                SectionInfoFactory.create(section, sectionedAdapter.getAdapterForSection(section))
        );
//        dialog.show(getParentFragmentManager(), DIALOG_TAG);
    }

    @Override
    public void onFooterRootViewClicked(@NonNull final NewsSection section, final int itemAdapterPosition) {
        final SectionItemInfoDialog dialog = SectionItemInfoDialog.getInstance(
                SectionItemInfoFactory.create(itemAdapterPosition, sectionedAdapter),
                SectionInfoFactory.create(section, sectionedAdapter.getAdapterForSection(section))
        );
//        dialog.show(getParentFragmentManager(), DIALOG_TAG);
    }
}
