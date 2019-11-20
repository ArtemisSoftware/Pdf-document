package com.titan.pdfdocumentlibrary.Elements;

import com.itextpdf.text.pdf.PdfPTable;

public class PDFTable {

    private PdfPTable table;
    private int numberOfCells, numberOfRows, numberOfInsertedCells;

    public PDFTable(){

        this.numberOfCells = 1;
        table = new PdfPTable(numberOfCells);
        table.setWidthPercentage(100);
        this.numberOfRows = 0;
        this.numberOfInsertedCells = 0;
    }

    //------------------------------
    //Local methods
    //------------------------------
}
