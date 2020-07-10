package com.titan.pdfdocument.sections;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;

public class MainSection extends Section {


    @Override
    protected Table getMainTable() {
        return new Table();
    }

    @Override
    protected void populateSection() {

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element. ALIGN_RIGHT;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;

        for(int aw = 0; aw < 2; aw++){
            table.addCell("Main Section", cellConfiguration);
        }

        Table colorTable = new Table(2);

        cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.backgroundColor = BaseColor.BLUE;


        Phrase phrase = new Phrase("Painting");
        table.addCell(phrase, cellConfiguration);
        table.addCell(phrase, cellConfiguration);


        CellConfiguration cellConfiguration_2 = new CellConfiguration();
        cellConfiguration_2.horizontalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration_2.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration_2.backgroundColor = BaseColor.MAGENTA;
        cellConfiguration_2.colSpan = 2;

        table.addCell(phrase, cellConfiguration_2);

    }
}
