package com.titan.pdfdocument.sections;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.titan.pdfdocumentlibrary.exception.PdfLineException;

public class UnevenSection extends Section {
    @Override
    protected Table getMainTable() {
        return new Table(3);
    }

    @Override
    protected void populateSection() {

        CellConfiguration cellConfiguration_1 = new CellConfiguration();
        cellConfiguration_1.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration_1.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration_1.backgroundColor = BaseColor.GREEN;

        CellConfiguration cellConfiguration_2 = new CellConfiguration();
        cellConfiguration_2.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration_2.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration_2.backgroundColor = BaseColor.YELLOW;

        CellConfiguration cellConfiguration_3 = new CellConfiguration();
        cellConfiguration_3.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration_3.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration_3.backgroundColor = BaseColor.ORANGE;
        cellConfiguration_3.colSpan = 2;



        CellConfiguration cellConfiguration [] = new CellConfiguration[]{cellConfiguration_1, cellConfiguration_2};

        CellConfiguration cellConfiguration_line [] = new CellConfiguration[]{cellConfiguration_3, cellConfiguration_2};

        Phrase phrases [] = new Phrase[]{ new Phrase("1 uneven phrase"), new Phrase("2 uneven phrase") };

        try {
            table.addLine(phrases, cellConfiguration[0]);
            table.addLine(phrases[0], cellConfiguration[1]);

            Phrase phrases_2[] = new Phrase[]{new Phrase("11 uneven phrase"), new Phrase("12 uneven phrase"), new Phrase("13 uneven phrase")};
            table.addLine(phrases_2, cellConfiguration);

            table.addEmptyCell(2);
            table.addCell(new Phrase("113 uneven phrase"));

            table.addEmptyLine();

            Phrase phrases_3[] = new Phrase[]{ new Phrase("4 uneven phrase"), new Phrase("41 uneven phrase") };
            table.addLine(phrases_3, cellConfiguration_line);
        }
        catch (PdfLineException e){
            e.printStackTrace();
        }
    }
}
