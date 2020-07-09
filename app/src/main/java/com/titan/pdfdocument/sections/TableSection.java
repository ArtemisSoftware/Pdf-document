package com.titan.pdfdocument.sections;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;

public class TableSection extends Section {


    @Override
    protected Table getMainTable() {
        return new Table(3);
    }

    @Override
    protected void populateSection() {

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;

        Table table1 = new Table(2);
        table1.addCell("table-1");
        table1.addCell("table-1-cellConfiguration", cellConfiguration);


        CellConfiguration cellConfiguration2 = new CellConfiguration();
        cellConfiguration2.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration2.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration2.backgroundColor = BaseColor.ORANGE;

        Table table2 = new Table(3);
        table2.addLine(new Phrase("table-2"),cellConfiguration2);
        table2.addCell("table-2-nothing");
        table2.addCell("table-2-cellConfiguration", cellConfiguration);
        table2.addCell("table-2-cellConfiguration2", cellConfiguration2);

        CellConfiguration cellConfiguration3 = new CellConfiguration();
        cellConfiguration3.backgroundColor = BaseColor.PINK;

        table.addCell(table1, cellConfiguration3);
        table.addCell(table2);
        table.addCell(table1, cellConfiguration);
    }
}
