package com.titan.pdfdocument.sections;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
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


        FontConfiguration fontConfiguration5 = new FontConfiguration();
        phrase = new Phrase(" ", fontConfiguration.getFont(12f));
        table.addCell(phrase);


        Paragraph paragraph = new Paragraph("NO LINE SPACING - A simple normal long phrase that will be used to show line spacing and I hope it works well to save time so I can move to a different task", fontConfiguration.getFont(12f));
        //paragraph.setLeading(0, 1.2f);
        PdfPCell chunky_12 = new PdfPCell();
        chunky_12.addElement(paragraph);
        table.addCell(chunky_12);



        paragraph = new Paragraph("1.2 LINE SPACING - A simple normal long phrase that will be used to show line spacing and I hope it works well to save time so I can move to a different task", fontConfiguration.getFont(12f));
        paragraph.setLeading(0, 1.2f);
        PdfPCell chunky_1 = new PdfPCell();
        chunky_1.addElement(paragraph);
        table.addCell(chunky_1);



        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.textLeading = 2.2f;

        paragraph = new Paragraph("2.2 LINE SPACING - A simple normal long phrase that will be used to show line spacing and I hope it works well to save time so I can move to a different task", fontConfiguration.getFont(12f));
//        paragraph.setLeading(0, 2.2f);
//        chunky_1 = new PdfPCell();
//        chunky_1.addElement(paragraph);
        table.addCell(paragraph, cellConfiguration);



        Chunk middlePart = new Chunk(" or Acrobat Professional 8.1 or above. To save completed forms, Acrobat Professional is required. For technical and accessibility assistance, contact the ", fontConfiguration.getFont(12f));
        paragraph = new Paragraph();
        paragraph.setLeading(0, 3.2f);
        paragraph.add(middlePart);
        PdfPCell chunky = new PdfPCell();
        chunky.addElement(paragraph);
        table.addCell(chunky);

    }
}
