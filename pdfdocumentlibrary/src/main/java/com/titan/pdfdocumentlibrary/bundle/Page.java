package com.titan.pdfdocumentlibrary.bundle;

import com.itextpdf.text.pdf.PdfPTable;
import com.titan.pdfdocumentlibrary.elements.Table;

import java.util.List;

public abstract class Page {




    public final int PAGE_ID;



    protected List<Integer> seccoes;

    public Page(int pageId){

        PAGE_ID = pageId;
        seccoes = getPageIndexes();
    }





    /**
     * Method to get the list of indexes for the sections of the page
     * @return a list od indexes
     */
    public List<Integer> getIndexes() {
        return seccoes;
    }



    /**
     * Metodo que permite obter um elemento da pagina
     * @param index o index do elemento
     * @return um elemento da pagina
     */
    public PdfPTable getElement(int index){

        try{

            Section section = getSection(seccoes.get(index));
            return section.table.getPdfTable();
        }
        catch(NullPointerException e){

            Table table = Section.getError("Seccao inexistente com o index: " + index + " - " /*+ SECCOES_PDF___idseccao_descricao.get(seccoes.get(index))*/, e);
            return table.getPdfTable();
        }
    }




    /**
     * Metodo que permite obter uma tabela de erro de uma determinada seccao
     * @param index o index da seccao
     * @return uma tabela de erro
     */
    /*
    protected TabelaPdf obterTabelaErro(int index){

        Fonte fonte = new Fonte();
        Paragraph erro = new Paragraph();

        erro.add(new Chunk("Seccao com o id: ", fonte.obterFonte(PdfIF.FONTE_10)));
        erro.add(new Chunk(index + " - " + SECCOES_PDF___idseccao_descricao.get(index), fonte.obterFonte_bold(PdfIF.FONTE_12, BaseColor.BLUE)));

        erro.add(new Chunk("  da pagina ", fonte.obterFonte(PdfIF.FONTE_10)));
        erro.add(new Chunk(this.getClass().getCanonicalName(), fonte.obterFonte_bold(PdfIF.FONTE_12, BaseColor.BLUE)));
        erro.add(new Chunk("  Não existe", fonte.obterFonte(PdfIF.FONTE_10)));

        return Seccao.obterTabelaErro(erro);
    }
*/



    //----------------------------------
    //Abstract
    //----------------------------------


    /**
     * Method to get all the indexes of a page
     * @return an array of indexes
     */
    protected abstract List<Integer> getPageIndexes();


    /**
     * Method to get a specific section
     * @param index the index of the section
     * @return a section
     */
    protected abstract Section getSection(int index);


}
