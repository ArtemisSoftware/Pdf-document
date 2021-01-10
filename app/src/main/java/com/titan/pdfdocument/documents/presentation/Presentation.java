package com.titan.pdfdocument.documents.presentation;

import android.content.Context;

import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.titan.pdfdocument.chapters.PresentationConfiguration;
import com.titan.pdfdocument.chapters.PresentationChapter;
import com.titan.pdfdocument.chapters.ImageChapter;
import com.titan.pdfdocument.chapters.TableChapter;
import com.titan.pdfdocument.chapters.events.PageHeaderfooter;
import com.titan.pdfdocumentlibrary.bundle.Chapter;
import com.titan.pdfdocumentlibrary.bundle.Template;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Presentation extends Template {

    public Presentation(Context context, File directory) {
        super(context, directory, new PresentationConfiguration());
    }



    @Override
    protected String getFileName() {
        return "one";
    }

    @Override
    protected List<Chapter> getChapters() {

        List<Chapter> pages = new ArrayList<>();
        pages.add(new PresentationChapter());
        pages.add(new ImageChapter(context));
        pages.add(new TableChapter());
        pages.add(new ImageChapter(context));
        pages.add(new PresentationChapter());
        pages.add(new ImageChapter(context));
        pages.add(new ImageChapter(context));
        pages.add(new TableChapter());
        return pages;
    }

    @Override
    protected PdfPageEventHelper getPageEvent() {
        return new PageHeaderfooter(this.getChapters().get(0).CHAPTER_ID);
    }



    @Override
    protected void setNewChapterConfigurations(PdfPageEvent pageEvent, int chapterNumber) {

        int chapterId = this.getChapters().get(chapterNumber).CHAPTER_ID;
        ((PageHeaderfooter)pageEvent).chapterId = chapterId;

        switch (chapterId) {

            case 1:
            case 3:

                document.setMargins(templateConfiguration.getLeftMargin(), templateConfiguration.getRightMargin(), ((PresentationConfiguration)templateConfiguration).getTopMarginHeader(), templateConfiguration.getBaseMargin());
                break;


            default:

                document.setMargins(templateConfiguration.getLeftMargin(), templateConfiguration.getRightMargin(), templateConfiguration.getTopMargin(), templateConfiguration.getBaseMargin());
                break;
        }

        document.newPage();

    }

    @Override
    protected void setNewPageConfigurations(PdfPageEvent pageEvent, Chapter chapter, int pageNumber) {

        if(paginacao.containsKey(pageNumber) == false){
            paginacao.put(pageNumber, chapter.CHAPTER_ID);
        }

        ((PageHeaderfooter)pageEvent).setRelations(paginacao);
    }


}
