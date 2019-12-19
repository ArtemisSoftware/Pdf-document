package com.titan.pdfdocumentlibrary;

import android.content.Context;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.titan.pdfdocumentlibrary.elements.PDFTable;

import java.util.ArrayList;

public abstract class Page {

    /**
     * Identificador da pagina
     */
    public final int ID_PAGINA;



    /**
     * lista de seccoes a figurar na pagina
     */
    protected ArrayList<Integer> seccoes;

    protected Context contexto;


    //private NotarioServico notario;


    public Page(Context contexto/*, NotarioServico notario*/, int idPagina){

        this.contexto = contexto;

        seccoes = new ArrayList<Integer>();
        adicionarSeccoes();

        ID_PAGINA = idPagina;

        //this.notario = notario;
    }



    /**
     * Metodo que permite obter o numero total de seccoes existentes na pagina
     */
    public int obterNumeroSeccoes() {
        return seccoes.size();
    }



    /**
     * Metodo que permite obter um elemento da pagina
     * @param index o index do elemento
     * @return um elemento da pagina
     */
    public PdfPTable obterElemento(int index){

        //notario.atualizar(NotarioServico.MENSAGEM_TEMPORARIA_PROGRESSO, "Seccao: " + PdfIF.SECCOES_PDF___idseccao_descricao.get(seccoes.get(index)), (index + 1), obterNumeroSeccoes());


        PDFTable tabela = obterSeccao(seccoes.get(index));

        /*
        try{

            return tabela.obterTabela();
        }
        catch(NullPointerException e){

            tabela = Section.obterTabelaErro("Seccao inexistente com o index: " + index + " - " + SECCOES_PDF___idseccao_descricao.get(seccoes.get(index)), e);
            return tabela.obterTabela();
        }
        */

        return null;
    }




    /**
     * Metodo que permite obter uma tabela de erro de uma determinada seccao
     * @param index o index da seccao
     * @return uma tabela de erro
     */
    protected PDFTable obterTabelaErro(int index){

        /*
        Fonte fonte = new Fonte();
        Paragraph erro = new Paragraph();

        erro.add(new Chunk("Seccao com o id: ", fonte.obterFonte(PdfIF.FONTE_10)));
        erro.add(new Chunk(index + " - " + SECCOES_PDF___idseccao_descricao.get(index), fonte.obterFonte_bold(PdfIF.FONTE_12, BaseColor.BLUE)));

        erro.add(new Chunk("  da pagina ", fonte.obterFonte(PdfIF.FONTE_10)));
        erro.add(new Chunk(this.getClass().getCanonicalName(), fonte.obterFonte_bold(PdfIF.FONTE_12, BaseColor.BLUE)));
        erro.add(new Chunk("  não existe", fonte.obterFonte(PdfIF.FONTE_10)));

        return Section.obterTabelaErro(erro);
        */

        return null;
    }



    //----------------------------------
    //Metodos abstratos
    //----------------------------------





    /**
     * Metodo que permite adicionar seccoes a lista de seccoes da pagina
     */
    protected abstract void adicionarSeccoes();


    /**
     * Metodo que permite obter uma determinada seccao
     * @param index o index da seccao
     * @return
     */
    protected abstract PDFTable obterSeccao(int index);
}
