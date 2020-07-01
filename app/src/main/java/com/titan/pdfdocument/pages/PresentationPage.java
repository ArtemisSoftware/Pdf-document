package com.titan.pdfdocument.pages;

import com.titan.pdfdocument.sections.MainSection;
import com.titan.pdfdocument.sections.NoBorderSection;
import com.titan.pdfdocumentlibrary.bundle.Page;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.models.Index;

import java.util.ArrayList;
import java.util.List;

public class PresentationPage extends Page {

    public PresentationPage() {
        super(1);
    }

    @Override
    protected List<Index> getPageIndexes() {


        List<Index> indexList = new ArrayList<>();

        indexList.add(new Index(1, "Main Section"));
        indexList.add(new Index(2, "No border Section"));

        return indexList;
    }

    @Override
    protected Section getSection(Index index) {

        Section section = null;


        switch (index.getId()) {


            case 1:

                section = new MainSection();
                break;

            case 2:

                section = new NoBorderSection();
                break;


            default:
                break;

        }

        return section;
    }
}
