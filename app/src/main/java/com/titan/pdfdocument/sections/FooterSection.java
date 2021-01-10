package com.titan.pdfdocument.sections;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;

public class FooterSection extends Section {


    private Table tableFooter;

    @Override
    protected Table getMainTable() {
        return new Table(new float[]{24, 24, 2}, 3* 175, false);
    }

    @Override
    protected void populateSection() {
        this.table = tableFooter;
    }


    /**
     * Method to create a table with the page number info
     * @param pageNumber the actual page number
     * @param pageTotal the total number of pages
     */
    public void addPageNumberFooter(int pageNumber, Image pageTotal){

        FontConfiguration font = new FontConfiguration();

        tableFooter = new Table(new float[]{24, 24, 2}, 3* 175, false);

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.height = 13;


        tableFooter.addLine( new Phrase("First line of footer", font.getFont(8)), cellConfiguration);
        tableFooter.addLine( new Phrase("Second line of footer", font.getFont(8)), cellConfiguration);
        tableFooter.addLine( new Phrase("Third line of footer", font.getFont(8)), cellConfiguration);
        tableFooter.addLine( new Phrase("Fourth line of footer", font.getFont(8)), cellConfiguration);


        addPageData(pageNumber, font);

        tableFooter.addCell(pageTotal);
        tableFooter.removeBorder(0,1,2,3);
        tableFooter.setBorder(Rectangle.TOP, 4);

    }


    public void setColoredFooterText(){

        FontConfiguration font = new FontConfiguration();

        tableFooter = new Table(new float[]{24, 24, 2}, 3* 175, false);

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.height = 13;


        tableFooter.addLine( new Phrase("First line of footer", font.getFont(8)), cellConfiguration);
        tableFooter.addLine( new Phrase("Second line of footer", font.getFont(8)), cellConfiguration);
        tableFooter.addLine( new Phrase("Third line of footer", font.getFont(8)), cellConfiguration);
        tableFooter.addLine( new Phrase("Fourth line of footer", font.getFont(8)), cellConfiguration);

    }


    public void setColoredFooter(){

        tableFooter = new Table(new float[]{50}, 3* 199, false);

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.height = 24;
        cellConfiguration.backgroundColor = BaseColor.RED;

        tableFooter.addCell("lolo", cellConfiguration);
    }


    private void addPageData(int pageNumber, FontConfiguration font){

        CellConfiguration cellConfiguration1 = new CellConfiguration();
        cellConfiguration1.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration1.height = 12;

        tableFooter.addCell(new Phrase("today's date", font.getFont(7)), cellConfiguration1);

        CellConfiguration cellConfiguration2 = null;
        try {
            cellConfiguration2 = (CellConfiguration) cellConfiguration1.clone();
            cellConfiguration2.horizontalAlign = Element.ALIGN_RIGHT;

            tableFooter.addCell(new Phrase(String.format("Página | %d de", pageNumber), font.getFont(7, true, BaseColor.GRAY)), cellConfiguration2);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
