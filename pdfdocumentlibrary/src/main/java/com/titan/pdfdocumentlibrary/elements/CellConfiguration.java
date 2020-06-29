package com.titan.pdfdocumentlibrary.elements;


import com.itextpdf.text.BaseColor;
import com.titan.pdfdocumentlibrary.util.PdfConstants;

/**
 * Class that represents the configuration of a table cell
 */
public class CellConfiguration {


    public int rowspan, colSpan;
    public int verticalAlign, horizontalAlign;

    public int border;

    public BaseColor backgroundColor;



    public CellConfiguration(){

        this.rowspan = PdfConstants.NO_VALUE;
        this.colSpan = PdfConstants.NO_VALUE;

        this.verticalAlign = PdfConstants.NO_VALUE;
        this.horizontalAlign = PdfConstants.NO_VALUE;

        this.border = PdfConstants.NO_VALUE;
        this.backgroundColor = BaseColor.WHITE;

        /*
        this.rotacao = AppIF.SEM_REGISTO;

        this.altura = AppIF.SEM_REGISTO;
        this.alinhamentoTopo = AppIF.SEM_REGISTO;
        this.alinhamentoEsquerda = AppIF.SEM_REGISTO;
        this.alinhamentoInferior = AppIF.SEM_REGISTO;
        this.alinhamentoDireita = AppIF.SEM_REGISTO;

        this.evento = null;

        this.sobrePosicaoCor = true;
        */
    }


}
