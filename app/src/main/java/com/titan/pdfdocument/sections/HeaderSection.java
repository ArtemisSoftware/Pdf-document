package com.titan.pdfdocument.sections;

import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.Table;

public class HeaderSection extends Section {
    @Override
    protected Table getMainTable() {
        return new Table(3);
    }

    @Override
    protected void populateSection() {

        table.addCell("header 1");
        table.addCell("header 2");
        table.addCell("header 3");
    }
}
