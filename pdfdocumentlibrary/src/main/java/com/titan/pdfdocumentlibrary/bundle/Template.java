package com.titan.pdfdocumentlibrary.bundle;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.titan.pdfdocumentlibrary.elements.TemplateConfiguration;
import com.titan.pdfdocumentlibrary.util.PdfConstants;
import com.titan.pdfdocumentlibrary.util.PdfUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public abstract class Template {


    protected File pdfFile;
    protected Document document;
    protected PdfWriter wp;


    protected final File DIRECTORY;

    protected Context context;
    protected TemplateConfiguration templateConfiguration;

    private int chapterNumber;

    protected HashMap<Integer, Integer> paginacao;
    private List<Chapter> chapters;


    public Template(Context context, File directory){

        this.context = context;
        DIRECTORY = directory;
        templateConfiguration = new TemplateConfiguration();

        pdfFile = null;
        document = new Document();
        chapterNumber = 0;

        paginacao = new HashMap<Integer, Integer>();
    }


    public Template(Context context, File directory, TemplateConfiguration templateConfiguration){

        this.context = context;
        DIRECTORY = directory;
        this.templateConfiguration = templateConfiguration;

        pdfFile = null;
        document = new Document();
        chapterNumber = 0;

        paginacao = new HashMap<Integer, Integer>();
    }


    /**
     * Method to create the pdf file
     */
    public void createFile() {

        pdfFile = PdfUtil.getFile(this, DIRECTORY, getFileName());
        chapters = getChapters();
        chapterNumber = 0;
        paginacao = new HashMap<Integer, Integer>();

        createDocument();
    }



    /**
     * Method to create a document
     */
    private void createDocument(){

        PdfPageEventHelper pageEventHelper = getPageEvent();

        document.setPageSize(templateConfiguration.getPageSize());
        document.setMargins(templateConfiguration.getLeftMargin(), templateConfiguration.getRightMargin(), templateConfiguration.getTopMargin(), templateConfiguration.getBaseMargin());

        try {

            FileOutputStream fOut = new FileOutputStream(pdfFile);
            wp = PdfWriter.getInstance(document, fOut);
            wp.setPageEvent(pageEventHelper);

            document.open();

            for (Chapter page: chapters) {
                page.create();
                addChapter(page);
            }

            PdfUtil.addMetaData(context, document, this);

        }
        catch (DocumentException | IOException de) {
            Log.e("PDFCreator", "Exception:" + de);
        }
        catch (Exception de) {
            Log.e("PDFCreator", "Exception:" + de);
        }

        finally{
            document.close();
        }
    }



    //----------------------
    //Chapter
    //----------------------



    /**
     * Method to add a chapter to the document
     * @param chapter the chapter to add
     */
    private void addChapter(Chapter chapter){

        setNewPageConfigurations(wp.getPageEvent(), chapter, wp.getPageNumber());
        setNewChapterConfigurations(wp.getPageEvent(), chapterNumber);

        try {

            for (int index = 0; index < chapter.getIndexes().size(); ++index) {
                setNewPageConfigurations(wp.getPageEvent(), chapter, wp.getPageNumber());
                try {

                    document.add(chapter.getElement(index));

                }
                catch(NullPointerException e){
                    document.add(PdfUtil.getErrorTable(e).getPdfTable());
                }

                addSpace();
                setNewPageConfigurations(wp.getPageEvent(), chapter, wp.getPageNumber());

            }
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }

        ++chapterNumber;
    }




    /**
     * Method to add a space between chapter sections
     * @throws DocumentException
     */
    private void addSpace() throws DocumentException {
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Paragraph(" ", new Font(Font.FontFamily.HELVETICA, templateConfiguration.getSectionSpacing())));
        document.add(paragraph);
    }





    //----------------------
    //
    //----------------------



    /**
     * Method to open the pdf
     */
    public void openPdf(){

        Uri uriFile;

        Intent intent = new Intent(Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uriFile = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", pdfFile);
        }
        else{
            uriFile = Uri.fromFile(pdfFile);
        }

        intent.setDataAndType(uriFile, PdfConstants.MIME_TYPE_APPLICATION_PDF);
        context.startActivity(intent);
    }




    //------------------------------------
    //Metodos abstratos
    //------------------------------------


    /**
     * Method that generates a file name
     * @return the name of the file
     */
    protected abstract String getFileName();


    /**
     * Method to get the chapters of the document
     * @return a list of chapters
     */
    protected abstract List<Chapter> getChapters();


    /**
     * Method to get the page event
     * @return a page event
     */
    protected abstract PdfPageEventHelper getPageEvent();


    /**
     * Method to set the configurations of a chapter
     * @param chapterNumber the number of the chapter
     */
    protected abstract void setNewChapterConfigurations(PdfPageEvent pageEvent, int chapterNumber);


    /**
     * Method to set the configurations of a page
     * @param pageEvent the page event
     * @param chapter the data of the chapter
     * @param pageNumber the number of the page
     */
    protected abstract void setNewPageConfigurations(PdfPageEvent pageEvent, Chapter chapter, int pageNumber);



}
