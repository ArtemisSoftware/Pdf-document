package com.titan.pdfdocumentlibrary.elements;

public interface TableWidthConverter {

    /**
     * Method to convert widths to another value
     * @param widths original widths
     * @return withs converted to new values
     */
    float [] convert(float [] widths);
}
