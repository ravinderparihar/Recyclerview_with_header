package com.bhs.myapplication.example3;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
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
import com.bhs.myapplication.library.Section;
import com.bhs.myapplication.library.SectionAdapter;
import com.bhs.myapplication.library.SectionedRecyclerViewAdapter;

import java.util.List;
import java.util.Random;

public class Example3Fragment extends Activity implements NewsSection.ClickListener {

    private static final String DIALOG_TAG = "SectionItemInfoDialogTag";

    private final Handler handler = new Handler();

    private SectionedRecyclerViewAdapter sectionedAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionedAdapter = new SectionedRecyclerViewAdapter();

        final NewsSection worldNews = new NewsSection(NewsSection.WORLD,
                getResources().getString(R.string.world_topic), this);
        final NewsSection bizNews = new NewsSection(NewsSection.BUSINESS,
                getResources().getString(R.string.biz_topic), this);
        final NewsSection techNews = new NewsSection(NewsSection.TECHNOLOGY,
                getResources().getString(R.string.tech_topic), this);
        final NewsSection sportsNews = new NewsSection(NewsSection.SPORTS,
                getResources().getString(R.string.sports_topic), this);

        sectionedAdapter.addSection(worldNews);
        sectionedAdapter.addSection(bizNews);
        sectionedAdapter.addSection(techNews);
        sectionedAdapter.addSection(sportsNews);

        loadNews(worldNews);
        loadNews(bizNews);
        loadNews(techNews);
        loadNews(sportsNews);

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(sectionedAdapter);

      
    }

  

    @Override
    public void onItemRootViewClicked(final NewsSection section, final int itemAdapterPosition) {
        final SectionItemInfoDialog dialog = SectionItemInfoDialog.getInstance(
                SectionItemInfoFactory.create(itemAdapterPosition, sectionedAdapter),
                SectionInfoFactory.create(section, sectionedAdapter.getAdapterForSection(section))
        );
//        dialog.show(getParentFragmentManager(), DIALOG_TAG);
    }

    @Override
    public void onFooterRootViewClicked(final NewsSection section, final int itemAdapterPosition) {
        final SectionItemInfoDialog dialog = SectionItemInfoDialog.getInstance(
                SectionItemInfoFactory.create(itemAdapterPosition, sectionedAdapter),
                SectionInfoFactory.create(section, sectionedAdapter.getAdapterForSection(section))
        );
//        dialog.show(getParentFragmentManager(), DIALOG_TAG);
    }

    @Override
    public void onFailedRootViewClicked(final NewsSection section) {
        loadNews(section);
    }

    private void loadNews(final NewsSection section) {
        changeSectionStateToLoading(section);

        handler.postDelayed(() -> {
            if (shouldFailToLoad()) {
                changeSectionStateToFailed(section);
            } else {
                changeSectionStateToLoaded(section);
            }
        }, getRandomTimeInMillis());
    }

    private void changeSectionStateToLoading(final NewsSection section) {
        final SectionAdapter sectionAdapter = sectionedAdapter.getAdapterForSection(section);

        // store info of current section state before changing its state
        final Section.State previousState = section.getState();
        final int previousItemsQty = section.getContentItemsTotal();
        final boolean hadFooter = section.hasFooter();

        section.setHasFooter(false);
        if (hadFooter) {
            sectionAdapter.notifyFooterRemoved();
        }

        section.setState(Section.State.LOADING);
        if (previousState == Section.State.LOADED) {
            sectionAdapter.notifyStateChangedFromLoaded(previousItemsQty);
        } else {
            sectionAdapter.notifyNotLoadedStateChanged(previousState);
        }
    }

    private void changeSectionStateToFailed(final NewsSection section) {
        final SectionAdapter sectionAdapter = sectionedAdapter.getAdapterForSection(section);

        // store info of current section state before changing its state
        final Section.State previousState = section.getState();
        final int previousItemsQty = section.getContentItemsTotal();
        final boolean hadFooter = section.hasFooter();

        section.setHasFooter(false);
        if (hadFooter) {
            sectionAdapter.notifyFooterRemoved();
        }

        section.setState(Section.State.FAILED);
        if (previousState == Section.State.LOADED) {
            sectionAdapter.notifyStateChangedFromLoaded(previousItemsQty);
        } else {
            sectionAdapter.notifyNotLoadedStateChanged(previousState);
        }
    }

    private void changeSectionStateToLoaded(final NewsSection section) {
        final SectionAdapter sectionAdapter = sectionedAdapter.getAdapterForSection(section);

        // store info of current section state before changing its state
        final Section.State previousState = section.getState();
        final boolean hadFooter = section.hasFooter();

        final List<News> list;
        final LoadNewsUseCase loadNewsUseCase = new LoadNewsUseCase();
        switch (section.getTopic()) {
            case NewsSection.WORLD:
                list = loadNewsUseCase.execute(this, R.array.news_world,
                        R.drawable.ic_public_black_48dp);
                break;
            case NewsSection.BUSINESS:
                list = loadNewsUseCase.execute(this, R.array.news_biz,
                        R.drawable.ic_business_black_48dp);
                break;
            case NewsSection.TECHNOLOGY:
                list = loadNewsUseCase.execute(this, R.array.news_tech,
                        R.drawable.ic_devices_other_black_48dp);
                break;
            case NewsSection.SPORTS:
                list = loadNewsUseCase.execute(this, R.array.news_sports,
                        R.drawable.ic_directions_run_black_48dp);
                break;
            default:
                throw new IllegalStateException("Invalid topic");
        }
        section.setList(list);

        section.setState(Section.State.LOADED);
        sectionAdapter.notifyStateChangedToLoaded(previousState);

        section.setHasFooter(true);
        if (!hadFooter) {
            sectionAdapter.notifyFooterInserted();
        }
    }

    private boolean shouldFailToLoad() {
        return new Random().nextInt((3 - 1) + 1) + 1 == 1;
    }

    private int getRandomTimeInMillis() {
        return new Random().nextInt((7000 - 3000) + 1) + 3000;
    }
}
