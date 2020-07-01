package com.titan.pdfdocument.sections;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;

public class NoBorderSection extends Section {



    @Override
    protected Table getMainTable() {
        return new Table();
    }

    @Override
    protected void populateSection() {

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.backgroundColor = BaseColor.GREEN;


        table.addCell(new Phrase("no border table with green cells"), cellConfiguration);
        table.addCell(new Phrase("2 no border table"), cellConfiguration);
        table.removeBorder();
    }
}
