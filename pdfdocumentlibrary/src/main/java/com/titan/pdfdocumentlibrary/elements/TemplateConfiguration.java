package com.titan.pdfdocumentlibrary.elements;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.titan.pdfdocumentlibrary.util.PdfConstants;

public class TemplateConfiguration {


    private Rectangle pageSize;

    private int leftMargin = 36;
    private int rightMargin = 50; //72
    private int topMargin = 60; //108
    private int baseMargin = 70 ;//180


    public TemplateConfiguration() {

        this.pageSize = PageSize.A4;
        this.leftMargin = PdfConstants.LEFT_MARGIN;
        this.rightMargin = PdfConstants.RIGHT_MARGIN;
        this.topMargin = PdfConstants.TOP_MARGIN;
        this.baseMargin = PdfConstants.BASE_MARGIN;
    }

    public Rectangle getPageSize() {
        return pageSize;
    }

    public int getLeftMargin() {
        return leftMargin;
    }

    public int getRightMargin() {
        return rightMargin;
    }

    public int getTopMargin() {
        return topMargin;
    }

    public int getBaseMargin() {
        return baseMargin;
    }
}
