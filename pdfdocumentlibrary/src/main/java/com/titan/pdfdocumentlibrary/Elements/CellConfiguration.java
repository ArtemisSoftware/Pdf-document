package com.titan.pdfdocumentlibrary.Elements;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfPCellEvent;

public class CellConfiguration {

    private final int NO_DATA = -1;

    /**
     * <b>row span</b> > <i>número de linhas de uma tabela que uma célula deve abranger</i>
     */
    private int rowspan;


    /**
     * <b>col span</b> > <i>número de colunas de uma tabela que uma célula deve abranger</i>
     */
    private int  colSpan;

    private int  rotacao, borda;
    private int alinhamentoVertical, alinhamentoHorizontal;
    private float altura, alinhamentoTopo, alinhamentoEsquerda, alinhamentoInferior, alinhamentoDireita;


    private BaseColor cor;


    private PdfPCellEvent evento;


    private boolean sobrePosicaoCor;



    public CellConfiguration(){

        this.rowspan = NO_DATA;
        this.colSpan = NO_DATA;
        this.rotacao = NO_DATA;

        this.alinhamentoVertical = NO_DATA;
        this.alinhamentoHorizontal = NO_DATA;
        this.cor = BaseColor.WHITE;

        this.altura = NO_DATA;
        this.alinhamentoTopo = NO_DATA;
        this.alinhamentoEsquerda = NO_DATA;
        this.alinhamentoInferior = NO_DATA;
        this.alinhamentoDireita = NO_DATA;

        this.borda = NO_DATA;
        this.evento = null;

        this.sobrePosicaoCor = true;
    }
}
