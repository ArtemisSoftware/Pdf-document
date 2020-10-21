package com.titan.pdfdocumentlibrary.elements;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.titan.pdfdocumentlibrary.util.PdfConstants;

/**
 * Class that represents the configuration of a table cell
 */
public class CellConfiguration implements Cloneable{


    public int rowspan, colSpan;
    public int verticalAlign, horizontalAlign;
    public int height, rotation;
    public float alignTop, alignLeft, alignBottom, alignRight;


    public int border;

    public BaseColor backgroundColor;

    public PdfPCellEvent event;
    public boolean overLapColor;

    public CellConfiguration(){

        this.rowspan = PdfConstants.NO_VALUE;
        this.colSpan = PdfConstants.NO_VALUE;

        this.verticalAlign = PdfConstants.NO_VALUE;
        this.horizontalAlign = PdfConstants.NO_VALUE;

        this.border = PdfConstants.NO_VALUE;
        this.backgroundColor = BaseColor.WHITE;
        this.height = PdfConstants.NO_VALUE;

        this.alignTop = PdfConstants.NO_VALUE;
        this.alignLeft = PdfConstants.NO_VALUE;
        this.alignBottom = PdfConstants.NO_VALUE;
        this.alignRight = PdfConstants.NO_VALUE;

        this.rotation = PdfConstants.NO_VALUE;


        this.event = null;

        this.overLapColor = true;

    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public void addFrame(float alignTop, float alignLeft, float alignBottom, float alignRight){
        this.alignTop = alignTop;
        this.alignLeft = alignLeft;
        this.alignBottom = alignBottom;
        this.alignRight = alignRight;
    }


    /**
     * Method that add a background color
     * @param backgroundColor the background color
     * @param overLapColor true it will overlap the existing color, false it will overlap only the color White
     */
    public void setOverLapColor(BaseColor backgroundColor, boolean overLapColor){
        this.backgroundColor = backgroundColor;
        this.overLapColor = overLapColor;
    }


}
