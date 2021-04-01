package com.titan.pdfdocumentlibrary.exception;

import com.titan.pdfdocumentlibrary.models.Index;

public class InexistentSectionException extends Exception {


    public InexistentSectionException(){}


    public InexistentSectionException(String message){
        super(message);
    }


    public InexistentSectionException(Index index){
        super("Inexistent section with index: " + index.getId() + " - " + index.getDescription());
    }



    public InexistentSectionException(Throwable cause){
        super(cause);
    }


    public InexistentSectionException(String message, Throwable cause){
        super(message, cause);
    }

}
