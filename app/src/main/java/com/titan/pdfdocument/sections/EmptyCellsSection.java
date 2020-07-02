package com.titan.pdfdocument.sections;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.titan.pdfdocumentlibrary.exception.PdfLineException;

public class EmptyCellsSection extends Section {

    @Override
    protected Table getMainTable() {
        return new Table(3);
    }

    @Override
    protected void populateSection() {

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.backgroundColor = BaseColor.DARK_GRAY;


        table.addEmptyCell();
        table.addLine(new Phrase("no border table"), cellConfiguration);

        try {
            table.addEmptyCell(2);
            table.addCell(new Phrase("no border table"), cellConfiguration);
        }
        catch (PdfLineException e){
            e.printStackTrace();
        }
    }
}
