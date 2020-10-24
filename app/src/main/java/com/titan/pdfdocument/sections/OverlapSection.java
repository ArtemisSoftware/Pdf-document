package com.titan.pdfdocument.sections;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;

public class OverlapSection extends Section {


    @Override
    protected Table getMainTable() {
        return new Table(3);
    }

    @Override
    protected void populateSection() {

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.rowspan = 2;
        cellConfiguration.backgroundColor = BaseColor.MAGENTA;
        cellConfiguration.rotation = 90;


        table.addCell(new Phrase("rotation"), cellConfiguration);


        CellConfiguration cellConfiguration_2 = new CellConfiguration();
        cellConfiguration_2.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration_2.horizontalAlign = Element.ALIGN_LEFT;


        table.addCell(new Phrase("no rotation"), cellConfiguration_2);
        table.addCell(new Phrase("no rotation"), cellConfiguration_2);

        table.addCell(new Phrase("no rotation"), cellConfiguration_2);
        table.addCell(new Phrase("no rotation"), cellConfiguration_2);


        CellConfiguration cellConfiguration_3 = new CellConfiguration();
        cellConfiguration_3.border = 0;
        cellConfiguration_3.setOverLapColor(BaseColor.ORANGE, false);
        table.formatCells(cellConfiguration_3);

    }
}
