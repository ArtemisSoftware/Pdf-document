package com.titan.pdfdocumentlibrary.elements;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.titan.pdfdocumentlibrary.util.PdfConstants;

public class Table {

    private PdfPTable table;


    private final int NUMBER_OF_CELLS;

    private int numberCells, numberRows, cellCounter;




    public Table(){

        this.NUMBER_OF_CELLS = 1;

        this.numberRows = 0;
        this.numberCells = 0;
        this.cellCounter = 0;

        this.table = new PdfPTable(NUMBER_OF_CELLS);
        this.table.setWidthPercentage(100);
    }



    public Table(int numberOfCells){

        this.NUMBER_OF_CELLS = numberOfCells;

        this.numberRows = 0;
        this.numberCells = 0;
        this.cellCounter = 0;

        this.table = new PdfPTable(NUMBER_OF_CELLS);
        this.table.setWidthPercentage(100);


        float cellDimensions [] = new float [NUMBER_OF_CELLS];

        for(int index = 0; index < NUMBER_OF_CELLS; ++index){
            cellDimensions[index] = 100f / NUMBER_OF_CELLS;
        }

        try {
            this.table.setWidths(cellDimensions);
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
    }





    /**
     * Method that returns a PdfPTable with the content added
     * @return a PdfPTable
     */
    public PdfPTable getPdfTable(){
        return table;
    }




    //------------------
    //Add cells
    //------------------


    /**
     * Method to insert a string on a table cell
     * @param text a string containing the text
     * @param cellConfiguration the configuration of the cell
     */
    public void addCell(String text, CellConfiguration cellConfiguration){

        PdfPCell cell = new PdfPCell(new Phrase(text));
        addCell(formatCell(cell, cellConfiguration));
    }


    /**
     * Method to insert a PdfPCell on a table cell
     * @param pdfPCell cell to insert
     */
    private void addCell(PdfPCell pdfPCell){

        table.addCell(pdfPCell);
        incrementNumberCells();
    }



    /**
     * Method to insert a phrase on a table cell
     * @param phrase a phrase
     * @param cellConfiguration the configuration of the cell
     */
    public void addCell(Phrase phrase, CellConfiguration cellConfiguration){

        PdfPCell cell = new PdfPCell(phrase);
        addCell(formatCell(cell, cellConfiguration));
    }



    //------------------
    //
    //------------------


    /**
     * Method that removes the table borders
     */
    public void removeBorder() {

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.border = 0;
        formatBorder(cellConfiguration);

    }



    //--------------
    //local methods -format
    //--------------


    /**
     * Method to format a cell
     * @param pdfPCell the pdfcell
     * @param cellConfiguration the cell configuration
     * @return a pdfcell
     */
    private PdfPCell formatCell(PdfPCell pdfPCell, CellConfiguration cellConfiguration){


        if(cellConfiguration.verticalAlign != PdfConstants.NO_VALUE){
            pdfPCell.setVerticalAlignment(cellConfiguration.verticalAlign);
        }

        if(cellConfiguration.horizontalAlign != PdfConstants.NO_VALUE){
            pdfPCell.setHorizontalAlignment(cellConfiguration.horizontalAlign);
        }
/*
        if(formato.obter_Rotacao() != AppIF.SEM_REGISTO){
            celula.setRotation(formato.obter_Rotacao());
        }


        if(formato.obter_SobrePosicaoCor() == true){
            celula.setBackgroundColor(formato.obter_CorFundo());
        }
        else{
*/
            pdfPCell.setBackgroundColor(cellConfiguration.backgroundColor);
     //  }

        if(cellConfiguration.rowspan != PdfConstants.NO_VALUE){
            pdfPCell.setRowspan(cellConfiguration.rowspan);
        }

        if(cellConfiguration.colSpan != PdfConstants.NO_VALUE){
            pdfPCell.setColspan(cellConfiguration.colSpan);
        }
 /*
        if(formato.obter_Altura() != AppIF.SEM_REGISTO ){
            celula.setFixedHeight(formato.obter_Altura());
        }

        if(formato.obter_Borda() != AppIF.SEM_REGISTO ){
            celula.setBorder(formato.obter_Borda());
        }

        if(formato.obter_Evento() != null){
            celula.setCellEvent(formato.obter_Evento());
        }



        if(formato.obter_AlturaTopo()  != AppIF.SEM_REGISTO){
            celula.setPaddingTop(formato.obter_AlturaTopo());
        }

        if(formato.obter_AlturaInferior()  != AppIF.SEM_REGISTO){
            celula.setPaddingBottom(formato.obter_AlturaInferior());
        }


        if(formato.obter_AlinhamentoEsquerda()  != AppIF.SEM_REGISTO){
            celula.setPaddingLeft(formato.obter_AlinhamentoEsquerda());
        }

        if(formato.obter_AlinhamentoDireita()  != AppIF.SEM_REGISTO){
            celula.setPaddingRight(formato.obter_AlinhamentoDireita());
        }
*/
        return pdfPCell;
    }




    /**
     * Method that allows to format the table border
     * @param cellConfiguration the configuration of the cells
     */
    public void formatBorder(CellConfiguration cellConfiguration){

        for (int indice = 0; indice < table.getRows().size(); ++indice) {

            for(int index = 0; index < table.getRow(indice).getCells().length; ++index){
                try{

                    formatCell(table.getRow(indice).getCells()[index], cellConfiguration);
                }
                catch(NullPointerException e){}
            }
        }
    }








    /**
     * Method to increment the number of cells in the table
     */
    private void incrementNumberCells(){
        incrementNumberCells(1);
    }


    /**
     * Method to increment the number of cells in the table
     * @param value the number of cells to increment
     */
    private void incrementNumberCells(int value){

        cellCounter += value;
        numberCells += value;

        if(numberCells == NUMBER_OF_CELLS){

            numberCells = 0;
            incrementNumberRows();
        }
    }


    /**
     * Method to increment the number of rows in the table
     */
    private void incrementNumberRows(){
        ++numberRows;
    }


}
