package com.titan.pdfdocumentlibrary.elements;

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








    //--------------
    //local methods
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

            if(celula.getBackgroundColor() == BaseColor.WHITE){
                celula.setBackgroundColor(formato.obter_CorFundo());
            }
        }

        if(formato.obter_RowSpan() != AppIF.SEM_REGISTO){
            celula.setRowspan(formato.obter_RowSpan());
        }

        if(formato.obter_ColSpan() != AppIF.SEM_REGISTO){
            celula.setColspan(formato.obter_ColSpan());
        }

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
