package com.titan.pdfdocument.chapters;

import android.content.Context;

import com.titan.pdfdocument.sections.ImageSection;
import com.titan.pdfdocument.sections.TableSection;
import com.titan.pdfdocumentlibrary.bundle.Chapter;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.models.Index;

import java.util.ArrayList;
import java.util.List;

public class ImageChapter extends Chapter {

    private Context context;

    public ImageChapter(Context context) {
        super(2);
        this.context = context;
    }


    @Override
    protected List<Index> getChapterIndexes() {

        List<Index> indexList = new ArrayList<>();

        indexList.add(new Index(1, "Image Section"));
        indexList.add(new Index(2, "Table Section"));
        return indexList;
    }

    @Override
    protected Section getSection(Index index) {
        Section section = null;


        switch (index.getId()) {


            case 1:

                section = new ImageSection(this.context);
                break;

            case 2:

                section = new TableSection();
                break;

            default:
                break;

        }

        return section;
    }
}
