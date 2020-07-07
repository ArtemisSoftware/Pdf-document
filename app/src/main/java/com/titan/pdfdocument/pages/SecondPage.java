package com.titan.pdfdocument.pages;

import android.content.Context;

import com.titan.pdfdocument.sections.ImageSection;
import com.titan.pdfdocumentlibrary.bundle.Page;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.models.Index;

import java.util.ArrayList;
import java.util.List;

public class SecondPage extends Page {

    private Context context;

    public SecondPage(Context context) {
        super(2);
        this.context = context;
    }


    @Override
    protected List<Index> getPageIndexes() {

        List<Index> indexList = new ArrayList<>();

        indexList.add(new Index(1, "Image Section"));
        return indexList;
    }

    @Override
    protected Section getSection(Index index) {
        Section section = null;


        switch (index.getId()) {


            case 1:

                section = new ImageSection(this.context);
                break;

            default:
                break;

        }

        return section;
    }
}
