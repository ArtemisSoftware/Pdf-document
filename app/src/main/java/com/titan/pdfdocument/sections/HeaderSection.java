package com.titan.pdfdocument.sections;

import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;

public class HeaderSection extends Section {
    @Override
    protected Table getMainTable() {
        return new Table(new float[]{35f, 35f, 30f}, 509, false);
    }

    @Override
    protected void populateSection() {

        table.addCell("header 1");
        table.addCell("header 2");
        table.addCell("header 3");
        table.addLine(new Phrase("Second line of the header"), new CellConfiguration());
    }
}
