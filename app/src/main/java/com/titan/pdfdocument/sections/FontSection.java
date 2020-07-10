package com.titan.pdfdocument.sections;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;

public class FontSection extends Section {
    @Override
    protected Table getMainTable() {
        return new Table();
    }

    @Override
    protected void populateSection() {

        FontConfiguration fontConfiguration = new FontConfiguration();
        Phrase phrase = new Phrase("A simple normal phrase", fontConfiguration.getFont(12f));
        table.addCell(phrase);
        phrase = new Phrase("A simple bold phrase", fontConfiguration.getFont(12f, true));
        table.addCell(phrase);

        FontConfiguration fontConfiguration2 = new FontConfiguration(Font.FontFamily.COURIER);
        phrase = new Phrase("A simple normal phrase in a different font", fontConfiguration2.getFont(14f, false, BaseColor.BLUE));
        table.addCell(phrase);
        phrase = new Phrase("A simple bold phrase in a different font", fontConfiguration2.getFont(14f, true, BaseColor.BLUE));
        table.addCell(phrase);


        FontConfiguration fontConfiguration3 = new FontConfiguration(Font.FontFamily.SYMBOL, Font.FontFamily.ZAPFDINGBATS);
        phrase = new Phrase("A simple 2 styles phrases", fontConfiguration3.getFont(16f));
        table.addCell(phrase);
        phrase = new Phrase("A simple 2 bold styles phrases", fontConfiguration3.getFont(16f, true));
        table.addCell(phrase);

        FontConfiguration fontConfiguration4 = new FontConfiguration("assets/fonts/MetaBooK-Roman.ttf");
        phrase = new Phrase("I do not accept your name, go default", fontConfiguration4.getFont(18f, false, BaseColor.PINK, true));
        table.addCell(phrase);
        phrase = new Phrase("I do not accept your name, go default", fontConfiguration4.getFont(18f, true, BaseColor.PINK, true));
        table.addCell(phrase);

    }
}
