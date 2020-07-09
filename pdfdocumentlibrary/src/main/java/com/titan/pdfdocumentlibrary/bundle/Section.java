package com.titan.pdfdocumentlibrary.bundle;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;

public abstract class Section {



    /**
     * Variable to control the debugging of the section<br><br>
     *
     *     if <b> true</b>:<br><br>
     *     1 > the borders of the section will be visible
     */
    private final boolean DEBUG;


    /**
     * Table that holds the section data
     */
    protected Table table;

    public Section(){
        this.DEBUG = false;
    }

    public Section(boolean debug){
        this.DEBUG = debug;
    }


    /**
     * Method to get the the section
     * @return the section
     */
    public Table getSection(){

        this.table = getMainTable();

//        try{
//
        populateSection();
//
//            LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Adicionada a tabela: " + this.getClass().getSimpleName(), AppConfigIF.DEBUG_LOG_CAT);
//        }
//        catch(NullPointerException | Pdf_Exception | CloneNotSupportedException e){
//            tabela.adicionarCelula(obterTabelaErro(e, "Erro ao adicionar a tabela: " + this.getClass().getSimpleName()));
//        }


        //if(!DEBUG) table.removeBorder();
        return table;
    }





    //----------------------
    //Abstract methods
    //----------------------


    /**
     * Method to get the main table of the section
     * @return a table
     */
    protected abstract Table getMainTable();



    /**
     * Method to populate section the section.<br>
     * All the data should be added to the table object of the section
     */
    protected abstract void populateSection() /*throws NullPointerException, Pdf_Exception, CloneNotSupportedException*/;

}
