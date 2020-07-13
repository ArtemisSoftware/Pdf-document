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
     * Metodo que permite adicionar o numero de página ao rodape
     * @param numeroPagina o numero da pagina
     * @param total
     */
    public void adicionarNumeroPagina(int numeroPagina, Image total){

        FontConfiguration font = new FontConfiguration();

        tableFooter = new Table(new float[]{24, 24, 2}, 3* 175, false);

        CellConfiguration cellConfiguration1 = new CellConfiguration();
        cellConfiguration1.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration1.height = 12;

        tableFooter.addCell(new Phrase("today's date", font.getFont(7)), cellConfiguration1);

        CellConfiguration cellConfiguration2 = null;
        try {
            cellConfiguration2 = (CellConfiguration) cellConfiguration1.clone();
            cellConfiguration2.horizontalAlign = Element.ALIGN_RIGHT;

            tableFooter.addCell(new Phrase(String.format("Página | %d de", numeroPagina), font.getFont(7, true, BaseColor.GRAY)), cellConfiguration2);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        PdfPCell cell = new PdfPCell(total);
        cell.setBorder(Rectangle.TOP);
        tableFooter.addCell(cell);
        tableFooter.setBorder(Rectangle.TOP);
    }


    /**
     * Metodo que permite adicionar a sede ao rodape
     * @param cb
     * @param document
     */
    public void adicinarSede(PdfContentByte cb, Document document){

        FontConfiguration font = new FontConfiguration();

        Phrase frase = new Phrase("First line of footer", font.getFont(8));
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, frase, document.left()/*(document.right() - document.left()) / 2 + document.leftMargin()*/, document.bottom() - 10, 0);

//        frase = new Phrase("Second line of footer", font.getFont(8));
//        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, frase, document.left()/*(document.right() - document.left()) / 2 + document.leftMargin()*/, document.bottom() - 18, 0);
//
//        frase = new Phrase("Third line of footer", font.getFont(8));
//        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, frase, document.left()/* (document.right() - document.left()) / 2 + document.leftMargin()*/, document.bottom() - 28, 0);
//
//        frase = new Phrase("Fourth line of footer", font.getFont(8));
//        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, frase, document.left()/*(document.right() - document.left()) / 2 + document.leftMargin()*/, document.bottom() - 38, 0);

    }


}
