package com.titan.pdfdocument.sections;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;

public class FooterSection extends Section {
    @Override
    protected Table getMainTable() {
        return new Table(new float[]{24, 24, 2}, 3* 175, false);
    }

    @Override
    protected void populateSection() {

        table.getPdfTable().getDefaultCell().setFixedHeight(12);
        table.getPdfTable().getDefaultCell().setBorder(Rectangle.TOP);

        FontConfiguration font = new FontConfiguration();
        table.addCell(new Phrase("today's date", font.getFont(7)));
        table.getPdfTable().getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
    }
}
