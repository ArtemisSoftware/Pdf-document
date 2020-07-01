package com.titan.pdfdocumentlibrary.bundle;

import com.itextpdf.text.pdf.PdfPTable;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.titan.pdfdocumentlibrary.exception.InexistentSectionException;
import com.titan.pdfdocumentlibrary.models.Index;

import java.util.List;

public abstract class Page {




    public final int PAGE_ID;



    protected List<Index> indexes;

    public Page(int pageId){

        PAGE_ID = pageId;
        indexes = getPageIndexes();
    }





    /**
     * Method to get the list of indexes for the sections of the page
     * @return a list od indexes
     */
    public List<Index> getIndexes() {
        return indexes;
    }



    /**
     * Method to get an element from the page
     * @param index the index of the page
     * @return an element
     */
    public PdfPTable getElement(int index){

        try{

            Section section = getSection(getIndex(index));
            return section.table.getPdfTable();
        }
        catch(InexistentSectionException e){

            Table table = Section.getError("Seccao inexistente com o index: " + index + " - " /*+ SECCOES_PDF___idseccao_descricao.get(indexes.get(index))*/, e);
            return table.getPdfTable();
        }
    }


    /**
     * Method to get the index data
     * @param index a position in the list of indexes
     * @return the index data
     * @throws InexistentSectionException
     */
    private Index getIndex(int index) throws InexistentSectionException{

        try{
            return indexes.get(index);
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new InexistentSectionException("The element in position: " + index + " does not correspond to an existing section", e);
        }
        catch (NullPointerException e){
            throw new InexistentSectionException("There are no sections avaliable", e);
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
    protected abstract List<Index> getPageIndexes();


    /**
     * Method to get a specific section
     * @param index the index of the section
     * @return a section
     */
    protected abstract Section getSection(Index index);


}
