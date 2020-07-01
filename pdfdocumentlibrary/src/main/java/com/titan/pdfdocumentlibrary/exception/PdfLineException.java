package com.titan.pdfdocumentlibrary.exception;

public class PdfLineException extends Exception {


    private static String PHRASES_EXCEED_CELLS = "The number of phrases exceeds the number of cells in the table row. Number of phrases: numberOfPhrases Number of cells in a row: numberOfCells";



    public PdfLineException(){}


    public PdfLineException(String message){
        super(message);
    }


    public PdfLineException(int numberOfPhrases, int numberOfCells){
        super(PHRASES_EXCEED_CELLS.replace("numberOfPhrases", numberOfPhrases + "").replace("numberOfCells", numberOfCells + ""));
    }



    public PdfLineException(Throwable cause){
        super(cause);
    }


    public PdfLineException(String message, Throwable cause){
        super(message, cause);
    }
}
