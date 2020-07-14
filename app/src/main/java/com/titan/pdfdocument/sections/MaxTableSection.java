package com.titan.pdfdocument.sections;

import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;

public class MaxTableSection extends Section {
    @Override
    protected Table getMainTable() {
        return new Table(1);
    }

    @Override
    protected void populateSection() {

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.height = 30;
        FontConfiguration fontConfiguration = new FontConfiguration();

        for(int index = 0; index < 22 *2; ++index){

            table.addLine(new Phrase("I am line number " + (index + 1), fontConfiguration.getFont(8)), cellConfiguration);
        }
    }
}
