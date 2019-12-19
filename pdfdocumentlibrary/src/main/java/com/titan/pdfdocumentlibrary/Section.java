package com.titan.pdfdocumentlibrary;

import android.content.Context;

import com.itextpdf.text.Paragraph;
import com.titan.pdfdocumentlibrary.elements.PDFTable;

public abstract class Section {


    protected Context contexto;

    /**
     * Tabela que possui os dados da seccao
     */
    protected PDFTable tabela;
    //protected Fonte fontes;




    public Section(Context contexto){

        this.contexto = contexto;

        //fontes = new Fonte(FONTE_TEXTO, FONTE_TEXTO_BOLD);

        this.tabela = obterTabelaSeccao();
    }




    //-----------------------------------
    //Metodos locais
    //-----------------------------------


    /**
     * Metodo que permite obter a seccao
     * @return a seccao
     */
    public PDFTable obterSeccao(){

        popularTabelaSeccao();
        //if(!DEBUG)tabela.removerRebordo();

        return tabela;
    }


    //-----------------------
    //Metodos locais - erros
    //-----------------------


    /**
     * Metodo que cria uma tabela de erro com uma excepcao
     * @param mensagem mensagem personalizada a figurar
     * @return uma tabela de erro
     */
    public static PDFTable obterTabelaErro(String mensagem){

        PDFTable tabela = new PDFTable();
        /*
        tabela.adicionarCelula(mensagem);

        tabela.pintarRebordo(BaseColor.RED);
        */
        return tabela;
    }


    /**
     * Metodo que cria uma tabela de erro com uma excepcao
     * @param mensagem mensagem personalizada a figurar
     * @return uma tabela de erro
     */
    public static PDFTable obterTabelaErro(Paragraph mensagem){

        PDFTable tabela = new PDFTable();
        /*
        tabela.adicionarCelula(mensagem);

        tabela.pintarRebordo(BaseColor.RED,3f);
        */
        return tabela;

    }


    /**
     * Metodo que cria uma tabela de erro com uma excepcao
     * @param mensagem mensagem personalizada a figurar
     * @param excepcao a exepcao
     * @return uma tabela de erro
     */
    public static PDFTable obterTabelaErro(String mensagem, Exception excepcao){


        PDFTable tabela = new PDFTable();
        /*
        tabela.adicionarCelula(mensagem);
        tabela.adicionarCelula(excepcao.getMessage());
        tabela.adicionarCelula(MetodosLog.formatarExcecao(excepcao));

        tabela.pintarRebordo(BaseColor.RED);
        */
        return tabela;

    }



    /**
     * Metodo que cria uma tabela de erro com uma excepcao
     * @param excepcao a exepcao
     * @param log uma mensagem de logo
     * @return uma tabela de erro
     */
    protected PDFTable obterTabelaErro(Exception excepcao, String log){

        PDFTable tabela = new PDFTable();
/*
        tabela.adicionarCelula(MetodosApp.obterNomeClasse(this.getClass()));
        tabela.adicionarCelula(MetodosLog.formatarExcecao(excepcao));
        tabela.adicionarCelula(excepcao.getMessage());


        tabela.pintarRebordo(BaseColor.RED);

        LogApp_v3.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto(log, AppConfigIF.DEBUG_LOG_CAT);
        LogApp_v3.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarExcecaoErro(log, excepcao);
        */
        return tabela;

    }



    //-----------------------------------
    //Abstract methods
    //-----------------------------------



    /**
     * Metodo que permite obter inicializar a tabela da seccao.<br>
     * Nesta inicializacao ficam definidas as dimensões das colunas da tabela
     * @return a tabela da seccao
     */
    protected abstract PDFTable obterTabelaSeccao();



    /**
     * Metodo que permite popular a tabela da seccao
     */
    protected abstract void popularTabelaSeccao();
}
