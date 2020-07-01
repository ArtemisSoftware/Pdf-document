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
     * Tabela que possui os dados da seccao
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


        if(!DEBUG) table.removeBorder();
        return table;
    }




    /**
     * Method that creates an error table with an exception
     * @param index the index data
     * @param exception the exception to present on the table
     * @return an error table
     */
    public static Table getError(String index, Exception exception){


        CellConfiguration cellConfigurationTitle = new CellConfiguration();
        cellConfigurationTitle.horizontalAlign = Element.ALIGN_LEFT;
        cellConfigurationTitle.backgroundColor = BaseColor.RED;

        Table table = new Table();
        table.addCell("ERROR", cellConfigurationTitle);

        table.addCell("Section not found: " + index + " - " /*+ SECCOES_PDF___idseccao_descricao.get(indexes.get(index))*/);

/*
        table.addCell(excepcao.getMessage());
        table.addCell(MetodosLog.formatarExcecao(excepcao));
*/
        table.setBorderColor(BaseColor.RED);
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
