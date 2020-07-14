package com.titan.pdfdocument.chapters;

import com.titan.pdfdocument.sections.MaxTableSection;
import com.titan.pdfdocumentlibrary.bundle.Chapter;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.models.Index;

import java.util.ArrayList;
import java.util.List;

public class TableChapter extends Chapter {

    public TableChapter() {
        super(3);
    }

    @Override
    protected List<Index> getChapterIndexes() {
        List<Index> indexList = new ArrayList<>();

        indexList.add(new Index(1, "Super table Section"));
        return indexList;
    }

    @Override
    protected Section getSection(Index index) {
        Section section = null;

        switch (index.getId()) {


            case 1:

                section = new MaxTableSection();
                break;

            default:
                break;

        }

        return section;
    }
}
