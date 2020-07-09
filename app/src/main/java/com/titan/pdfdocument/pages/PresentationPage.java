package com.titan.pdfdocument.pages;

import com.titan.pdfdocument.sections.EmptyCellsSection;
import com.titan.pdfdocument.sections.MainSection;
import com.titan.pdfdocument.sections.NoBorderSection;
import com.titan.pdfdocument.sections.UnevenSection;
import com.titan.pdfdocument.sections.UnevenTableSection;
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
        indexList.add(new Index(3, "Uneven Section"));
        indexList.add(new Index(4, "Empty cells Section"));
        indexList.add(new Index(5, "Uneven table Section"));
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

            case 3:

                section = new UnevenSection();
                break;

            case 4:

                section = new EmptyCellsSection();
                break;

            case 5:

                section = new UnevenTableSection();
                break;


            default:
                break;

        }

        return section;
    }
}
