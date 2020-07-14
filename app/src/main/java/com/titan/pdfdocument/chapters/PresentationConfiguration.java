package com.titan.pdfdocument.chapters;

import com.titan.pdfdocumentlibrary.elements.TemplateConfiguration;

public class PresentationConfiguration extends TemplateConfiguration {

    private int topMarginHeader;

    public PresentationConfiguration() {
        super();
        topMarginHeader = 115;
    }

    public int getTopMarginHeader() {
        return topMarginHeader;
    }
}
