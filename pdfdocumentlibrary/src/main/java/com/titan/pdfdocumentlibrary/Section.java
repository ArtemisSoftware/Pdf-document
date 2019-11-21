package com.titan.pdfdocumentlibrary;

import android.content.Context;

import com.titan.pdfdocumentlibrary.elements.PDFTable;

public abstract class Section {


    protected Context contexto;

    /**
     * Tabela que possui os dados da seccao
     */
    protected PDFTable table;
    //protected Fonte fontes;




    public Section(Context contexto, String idTarefa){

        this.contexto = contexto;

        //fontes = new Fonte(FONTE_TEXTO, FONTE_TEXTO_BOLD);

        this.table = getTable();
    }



    //-----------------------------------
    //Abstract methods
    //-----------------------------------

    /**
     * Metodo que permite obter inicializar a tabela da seccao.<br>
     * Nesta inicializacao ficam definidas as dimensões das colunas da tabela
     * @return a tabela da seccao
     */
    protected abstract PDFTable getTable();



    /**
     * Metodo que permite popular a tabela da seccao
     */
    protected abstract void populate() throws NullPointerException/*, Pdf_Exception*/, CloneNotSupportedException;

}
