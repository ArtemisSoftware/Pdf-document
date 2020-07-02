package com.titan.pdfdocumentlibrary.bundle;

import com.itextpdf.text.pdf.PdfPTable;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.titan.pdfdocumentlibrary.exception.InexistentSectionException;
import com.titan.pdfdocumentlibrary.models.Index;
import com.titan.pdfdocumentlibrary.util.PdfUtil;

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

            if(section == null){
                throw new InexistentSectionException("The element in position: " + index + " with id " + getIndex(index).getId() + " and description " +getIndex(index).getDescription() + " does not correspond to an existing section for the page " + this.getClass().getSimpleName());
            }
            return section.getSection().getPdfTable();
        }
        catch(InexistentSectionException e){

            return PdfUtil.getErrorTable(e).getPdfTable();
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
        catch (IndexOutOfBoundsException e){
            throw new InexistentSectionException("The element in position: " + index + " does not correspond to an existing section for the page " + this.getClass().getSimpleName(), e);
        }
        catch (NullPointerException e){
            throw new InexistentSectionException("There are no sections avaliable for the page " + this.getClass().getSimpleName(), e);
        }
    }



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