package com.bhs.myapplication.info;

import com.bhs.myapplication.library.Section;
import com.bhs.myapplication.library.SectionAdapter;


public class SectionInfoFactory {

    public static SectionInfo create(final Section section, final SectionAdapter sectionAdapter) {
        return new SectionInfo(
                sectionAdapter.getSectionPosition(),
                section.hasHeader(),
                section.hasHeader() ? sectionAdapter.getHeaderPosition() : null,
                section.hasFooter(),
                section.hasFooter() ? sectionAdapter.getFooterPosition() : null
        );
    }
}
