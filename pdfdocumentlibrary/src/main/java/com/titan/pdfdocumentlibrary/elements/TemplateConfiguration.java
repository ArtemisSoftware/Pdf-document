package com.titan.pdfdocumentlibrary.elements;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.titan.pdfdocumentlibrary.util.PdfConstants;

public class TemplateConfiguration {


    private Rectangle pageSize;
    private int leftMargin, rightMargin, topMargin, baseMargin;
    private float sectionSpacing;


    public TemplateConfiguration() {

        this.pageSize = PageSize.A4;
        this.leftMargin = PdfConstants.LEFT_MARGIN;
        this.rightMargin = PdfConstants.RIGHT_MARGIN;
        this.topMargin = PdfConstants.TOP_MARGIN;
        this.baseMargin = PdfConstants.BASE_MARGIN;
        this.sectionSpacing = PdfConstants.SECTION_SPACING;
    }

    public TemplateConfiguration(Rectangle pageSize, int leftMargin, int rightMargin, int topMargin, int baseMargin, float sectionSpacing) {

        this.pageSize = pageSize;
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;
        this.topMargin = topMargin;
        this.baseMargin = baseMargin;
        this.sectionSpacing = sectionSpacing;
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

    public float getSectionSpacing() {
        return sectionSpacing;
    }
}
