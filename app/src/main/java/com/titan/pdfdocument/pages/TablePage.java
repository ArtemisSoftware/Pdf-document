package com.titan.pdfdocument.pages;

import com.titan.pdfdocumentlibrary.bundle.Page;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.models.Index;

import java.util.ArrayList;
import java.util.List;

public class TablePage extends Page {

    public TablePage() {
        super(3);
    }

    @Override
    protected List<Index> getPageIndexes() {
        List<Index> indexList = new ArrayList<>();

        indexList.add(new Index(1, "Super table Section"));
        return indexList;
    }

    @Override
    protected Section getSection(Index index) {
        Section section = null;

        switch (index.getId()) {


            case 1:

                //section = new MainSection();
                break;

            default:
                break;

        }

        return section;
    }
}
