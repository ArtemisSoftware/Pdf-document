package com.titan.pdfdocument;

import android.content.Context;

import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.titan.pdfdocument.pages.PresentationConfiguration;
import com.titan.pdfdocument.pages.PresentationPage;
import com.titan.pdfdocument.pages.SecondPage;
import com.titan.pdfdocument.pages.TablePage;
import com.titan.pdfdocument.pages.events.PageHeaderfooter;
import com.titan.pdfdocumentlibrary.bundle.Page;
import com.titan.pdfdocumentlibrary.bundle.Template;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Pesentation extends Template {

    public Pesentation(Context context, File directory) {
        super(context, directory, new PresentationConfiguration());
    }



    @Override
    protected String getFileName() {
        return "one";
    }

    @Override
    protected List<Page> getPages() {

        List<Page> pages = new ArrayList<>();
        pages.add(new PresentationPage());
        pages.add(new SecondPage(context));
        pages.add(new TablePage());
        pages.add(new SecondPage(context));
        pages.add(new PresentationPage());
        pages.add(new SecondPage(context));
        pages.add(new SecondPage(context));
        return pages;
    }

    @Override
    protected PdfPageEventHelper getPageEvent() {
        return new PageHeaderfooter();
    }



    @Override
    protected void setNewChapterConfigurations(int chapterNumber) {


        int pageId = this.getPages().get(chapterNumber).CHAPTER_ID;

        switch (pageId) {

            case 1:
            case 3:

                documento.setMargins(templateConfiguration.getLeftMargin(), templateConfiguration.getRightMargin(), ((PresentationConfiguration)templateConfiguration).getTopMarginHeader(), templateConfiguration.getBaseMargin());
                break;


            default:

                documento.setMargins(templateConfiguration.getLeftMargin(), templateConfiguration.getRightMargin(), templateConfiguration.getTopMargin(), templateConfiguration.getBaseMargin());
                break;
        }

        documento.newPage();

    }

    @Override
    protected void setNewPageConfigurations(PdfPageEvent pageEvent, Page chapter, int pageNumber) {

        if(paginacao.containsKey(pageNumber) == false){
            paginacao.put(pageNumber, chapter.CHAPTER_ID);
        }

        ((PageHeaderfooter)pageEvent).setRelations(paginacao);
    }


}
