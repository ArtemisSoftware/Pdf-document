package com.titan.pdfdocumentlibrary.models;

import com.titan.pdfdocumentlibrary.util.PdfConstants;

public class Index {

    private int id;
    private String description;

    public Index(int id) {
        this.id = id;
        this.description = PdfConstants.NO_DATA;
    }

    public Index(int id, String description) {
        this.id = id;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
