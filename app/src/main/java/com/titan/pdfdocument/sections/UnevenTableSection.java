package com.titan.pdfdocument.sections;

import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.Table;

public class UnevenTableSection extends Section {

    @Override
    protected Table getMainTable() {

        float[] widhts = new float[]{15f, 50f, 30f};
        Table table = new Table(widhts/*, converter1*/);
        return table;
    }

    @Override
    protected void populateSection() {

        table.addCell("unevenTableCellsSection");
        table.addCell("unevenTableCellsSection");
        table.addCell("unevenTableCellsSection");

        table.addCell("unevenTableCellsSection-2");
        table.addCell("unevenTableCellsSection-2");
        table.addCell("unevenTableCellsSection-2");
    }
}
