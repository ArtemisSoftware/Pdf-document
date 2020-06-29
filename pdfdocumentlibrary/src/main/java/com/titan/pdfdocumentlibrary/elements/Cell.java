package com.titan.pdfdocumentlibrary.elements;

public class Cell {

    private final int NO_VALUE = -1;


    public int rowspan, colSpan;
    public int verticalAlign, horizontalAlign;

    public Cell(){

        this.rowspan = NO_VALUE;
        this.colSpan = NO_VALUE;

        this.verticalAlign = NO_VALUE;
        this.horizontalAlign = NO_VALUE;


        /*
        this.rotacao = AppIF.SEM_REGISTO;


        this.cor = BaseColor.WHITE;

        this.altura = AppIF.SEM_REGISTO;
        this.alinhamentoTopo = AppIF.SEM_REGISTO;
        this.alinhamentoEsquerda = AppIF.SEM_REGISTO;
        this.alinhamentoInferior = AppIF.SEM_REGISTO;
        this.alinhamentoDireita = AppIF.SEM_REGISTO;

        this.borda = AppIF.SEM_REGISTO;
        this.evento = null;

        this.sobrePosicaoCor = true;
        */
    }


}
