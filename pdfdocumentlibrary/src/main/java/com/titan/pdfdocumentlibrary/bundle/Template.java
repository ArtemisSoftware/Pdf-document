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
import com.titan.pdfdocumentlibrary.exception.PdfCreationException;
import com.titan.pdfdocumentlibrary.util.PdfConstants;
import com.titan.pdfdocumentlibrary.util.PdfReport;
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

    public PdfReport pdfReport;

    public Template(Context context, File directory){

        this.context = context;
        DIRECTORY = directory;
        templateConfiguration = new TemplateConfiguration();

        pdfFile = null;
        document = new Document();
        chapterNumber = 0;

        paginacao = new HashMap<Integer, Integer>();

        pdfReport = new PdfReport();
    }


    public Template(Context context, File directory, TemplateConfiguration templateConfiguration){

        this.context = context;
        DIRECTORY = directory;
        this.templateConfiguration = templateConfiguration;

        pdfFile = null;
        document = new Document();
        chapterNumber = 0;

        paginacao = new HashMap<Integer, Integer>();
        pdfReport = new PdfReport();
    }


    /**
     * Method to create the pdf file
     */
    public void createFile() throws PdfCreationException {

        pdfFile = PdfUtil.getFile(this, DIRECTORY, getFileName());
        chapters = getChapters();
        chapterNumber = 0;
        paginacao = new HashMap<Integer, Integer>();

        createDocument();
    }



    /**
     * Method to create a document
     */
    private void createDocument() throws PdfCreationException {

        PdfPageEventHelper pageEventHelper = getPageEvent();

        document.setPageSize(templateConfiguration.getPageSize());
        document.setMargins(templateConfiguration.getLeftMargin(), templateConfiguration.getRightMargin(), templateConfiguration.getTopMargin(), templateConfiguration.getBaseMargin());

        pdfReport.report.add("PDF init creation ");
        pdfReport.report.add("PDF DIRECTORY: " + DIRECTORY);
        pdfReport.report.add("PDF file: " + pdfFile.getAbsolutePath());

        try {

            FileOutputStream fOut = new FileOutputStream(pdfFile);
            wp = PdfWriter.getInstance(document, fOut);
            wp.setPageEvent(pageEventHelper);

            document.open();

            for (Chapter page: chapters) {
                pdfReport.report.add("Chapter: " + page.CHAPTER_ID);
                page.create();
                addChapter(page);
            }

            PdfUtil.addMetaData(context, document, this);

        }
        catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de.getMessage());
            pdfReport.report.add("DocumentException: " + de);
            pdfReport.errorCreating = true;
        }
        catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e.getMessage());
            pdfReport.report.add("ioException: " + e);
            pdfReport.errorCreating = true;
        }
        catch (Exception ex) {
            Log.e("PDFCreator", "Exception:" + ex.getMessage());
            pdfReport.report.add("Exception: " + ex.getMessage());
            pdfReport.errorCreating = true;
        }

        finally{
            document.close();
        }

        pdfReport.report.add("Pdf creation complete");

        if(pdfReport.errorCreating == true){
            throw new PdfCreationException("Error creating pdf", pdfReport);
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
     * Method to get the pdf file
     * @return a file
     */
    public File getPdfFile() {
        return pdfFile;
    }

    /**
     * Method to open the pdf
     */
    public void openPdf(){

        if(pdfReport.errorCreating){
            return;
        }

        pdfReport.report.add("");
        pdfReport.report.add("Pdf start opening...");
        pdfReport.report.add("File: " + pdfFile.getAbsolutePath());

        Uri uriFile;

        Intent intent = new Intent(Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uriFile = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", pdfFile);
            pdfReport.report.add("DocumentURI (1): " + uriFile.toString());
        }
        else{
            uriFile = Uri.fromFile(pdfFile);
            pdfReport.report.add("DocumentURI (2): " + uriFile.toString());
        }

        intent.setDataAndType(uriFile, PdfConstants.MIME_TYPE_APPLICATION_PDF);

        try {
            context.startActivity(intent);
            pdfReport.report.add("Pdf opened with success");
        }
        catch(Exception e) {
            pdfReport.report.add("Exception: " + e.toString());
        }
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
