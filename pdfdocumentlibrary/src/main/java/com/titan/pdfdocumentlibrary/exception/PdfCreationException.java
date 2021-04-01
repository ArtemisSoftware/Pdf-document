package com.titan.pdfdocumentlibrary.exception;

import com.titan.pdfdocumentlibrary.util.PdfReport;

public class PdfCreationException extends Exception {

    public PdfReport pdfReport;

    public PdfCreationException(){
        pdfReport = new PdfReport();
    }


    public PdfCreationException(String message){
        super(message);
        pdfReport = new PdfReport();
    }

    public PdfCreationException(String message, PdfReport pdfReport){
        super(message);
        this.pdfReport = pdfReport;
    }
}
