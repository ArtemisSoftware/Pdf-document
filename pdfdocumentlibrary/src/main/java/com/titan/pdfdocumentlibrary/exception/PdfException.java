package com.titan.pdfdocumentlibrary.exception;

public class PdfException extends Exception {


    public PdfException(){}


    public PdfException(String message){
        super(message);
    }


    public PdfException(Throwable cause){
        super(cause);
    }


    public PdfException(String message, Throwable cause){
        super(message, cause);
    }
}
