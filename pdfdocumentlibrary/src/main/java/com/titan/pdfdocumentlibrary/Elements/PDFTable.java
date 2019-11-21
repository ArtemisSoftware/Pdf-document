package com.titan.pdfdocumentlibrary.Elements;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PDFTable {

    private PdfPTable table;
    private final int NUMBER_OF_CELLS;
    private int numberOfRows, numberOfInsertedCells;

    public PDFTable(){

        this.NUMBER_OF_CELLS = 1;
        this.numberOfRows = 0;
        this.numberOfInsertedCells = 0;

        table = new PdfPTable(NUMBER_OF_CELLS);
        table.setWidthPercentage(100);

    }

    //------------------------------
    //Local methods
    //------------------------------


    /**
     * Metodo que aplica uma formatacao à celula<br>
     * <b>Campos formatados</b>:
     * <br>- <i>Alinhamento horizontal</i>
     * <br>- <i>Alinhamento vertical</i>
     * <br>- <i>cor de fundo</i>
     * <br>- <i>Padding</i>
     * <br>- <i>Altura</i>
     * <br>- <i>RowSpan</i>
     * <br>- <i>ColSpan</i>
     * @param celula celula formatar
     * @param cellConfiguration a formatacao a aplicar
     * @return uma celula formatada
     */
    private PdfPCell formatarCelula(PdfPCell celula, CellConfiguration cellConfiguration){

/*
        if(cellConfiguration.obter_AlinhamentoVertical() != AppIF.SEM_REGISTO){
            celula.setVerticalAlignment(cellConfiguration.obter_AlinhamentoVertical());
        }

        if(cellConfiguration.obter_AlinhamentoHorizontal() != AppIF.SEM_REGISTO){
            celula.setHorizontalAlignment(cellConfiguration.obter_AlinhamentoHorizontal());
        }

        if(cellConfiguration.obter_Rotacao() != AppIF.SEM_REGISTO){
            celula.setRotation(cellConfiguration.obter_Rotacao());
        }


        if(cellConfiguration.obter_SobrePosicaoCor() == true){
            celula.setBackgroundColor(cellConfiguration.obter_CorFundo());
        }
        else{

            if(celula.getBackgroundColor() == BaseColor.WHITE){
                celula.setBackgroundColor(cellConfiguration.obter_CorFundo());
            }
        }

        if(cellConfiguration.obter_RowSpan() != AppIF.SEM_REGISTO){
            celula.setRowspan(cellConfiguration.obter_RowSpan());
        }

        if(cellConfiguration.obter_ColSpan() != AppIF.SEM_REGISTO){
            celula.setColspan(cellConfiguration.obter_ColSpan());
        }

        if(cellConfiguration.obter_Altura() != AppIF.SEM_REGISTO ){
            celula.setFixedHeight(cellConfiguration.obter_Altura());
        }

        if(cellConfiguration.obter_Borda() != AppIF.SEM_REGISTO ){
            celula.setBorder(cellConfiguration.obter_Borda());
        }

        if(cellConfiguration.obter_Evento() != null){
            celula.setCellEvent(cellConfiguration.obter_Evento());
        }



        if(cellConfiguration.obter_AlturaTopo()  != AppIF.SEM_REGISTO){
            celula.setPaddingTop(cellConfiguration.obter_AlturaTopo());
        }

        if(cellConfiguration.obter_AlturaInferior()  != AppIF.SEM_REGISTO){
            celula.setPaddingBottom(cellConfiguration.obter_AlturaInferior());
        }


        if(cellConfiguration.obter_AlinhamentoEsquerda()  != AppIF.SEM_REGISTO){
            celula.setPaddingLeft(cellConfiguration.obter_AlinhamentoEsquerda());
        }

        if(cellConfiguration.obter_AlinhamentoDireita()  != AppIF.SEM_REGISTO){
            celula.setPaddingRight(cellConfiguration.obter_AlinhamentoDireita());
        }
*/
        return celula;

    }


}
