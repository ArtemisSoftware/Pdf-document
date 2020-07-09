package com.titan.pdfdocument.sections;

import android.content.Context;

import com.itextpdf.text.Element;
import com.titan.pdfdocument.R;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;

public class ImageSection extends Section {

    private Context context;

    public ImageSection(Context context) {
        this.context = context;
    }

    @Override
    protected Table getMainTable() {
        return new Table(3);
    }

    @Override
    protected void populateSection() {
        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;


        table.addCell(context.getResources(), R.drawable.img_batman);
        table.addCell(context.getResources(), com.titan.pdfdocumentlibrary.R.drawable.tst_image);
        table.addCell(context.getResources(), com.titan.pdfdocumentlibrary.R.drawable.tst_image);

        table.addCell(context.getResources(), R.drawable.img_batman, cellConfiguration);
        table.addCell(context.getResources(), com.titan.pdfdocumentlibrary.R.drawable.tst_image);
        table.addCell(context.getResources(), R.drawable.ic_launcher_background, cellConfiguration);
    }
}
