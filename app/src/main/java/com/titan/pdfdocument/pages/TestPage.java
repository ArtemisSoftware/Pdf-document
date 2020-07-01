package com.titan.pdfdocument.pages;

import com.titan.pdfdocument.sections.MainSection;
import com.titan.pdfdocumentlibrary.bundle.Page;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.exception.InexistentSectionException;
import com.titan.pdfdocumentlibrary.models.Index;

import java.util.ArrayList;
import java.util.List;

public class TestPage extends Page {

    public TestPage() {
        super(1);
    }

    @Override
    protected List<Index> getPageIndexes() {


        List<Index> indexList = new ArrayList<>();

        indexList.add(new Index(1, "Main Section"));

        return indexList;
    }

    @Override
    protected Section getSection(Index index) {

        Section section = null;


        switch (index.getId()) {


            case 1:

                section = new MainSection();
                break;



            default:
                break;

        }

        return section;
    }
}
