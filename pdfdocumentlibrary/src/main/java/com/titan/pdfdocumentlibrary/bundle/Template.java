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


    protected File ficheiroPdf;
    protected Document documento;
    protected PdfWriter wp;


    protected final File DIRECTORY;

    protected Context context;
    protected TemplateConfiguration templateConfiguration;

    private int chapterNumber;

    protected HashMap<Integer, Integer> paginacao;
    private List<Page> pages;


    public Template(Context context, File directory){

        this.context = context;
        DIRECTORY = directory;
        templateConfiguration = new TemplateConfiguration();

        ficheiroPdf = null;
        documento = new Document();
        chapterNumber = 0;

        paginacao = new HashMap<Integer, Integer>();
    }


    public Template(Context context, File directory, TemplateConfiguration templateConfiguration){

        this.context = context;
        DIRECTORY = directory;
        this.templateConfiguration = templateConfiguration;

        ficheiroPdf = null;
        documento = new Document();
        chapterNumber = 0;

        paginacao = new HashMap<Integer, Integer>();
    }


    /**
     * Method to create the pdf file
     */
    public void createFile() {

        ficheiroPdf = PdfUtil.getFile(this, DIRECTORY, getFileName());
        pages = getPages();
        chapterNumber = 0;
        paginacao = new HashMap<Integer, Integer>();

        criarDocumento();
    }


    /**
     * Metodo que cria um ficheiro pdf representativo do acordo
     * param ficheiro ficheiro a ser criado
     */
    private void criarDocumento(){

        PdfPageEventHelper pageEventHelper = getPageEvent();

        documento.setPageSize(templateConfiguration.getPageSize());
        documento.setMargins(templateConfiguration.getLeftMargin(), templateConfiguration.getRightMargin(), templateConfiguration.getTopMargin(), templateConfiguration.getBaseMargin());

        try {

            FileOutputStream fOut = new FileOutputStream(ficheiroPdf);
            wp = PdfWriter.getInstance(documento, fOut);
            wp.setPageEvent(pageEventHelper);

            documento.open();

            for (Page page: pages) {
                addPage(page);
            }

            PdfUtil.addMetaData(context, documento, this);

        }
        catch (DocumentException | IOException de) {
            Log.e("PDFCreator", "Exception:" + de);
        }
        catch (Exception de) {
            Log.e("PDFCreator", "Exception:" + de);
        }

        finally{
            documento.close();
        }
    }



    //----------------------
    //Pages
    //----------------------



    /**
     * Method to add a page to the document
     * @param pagina the page to add
     */
    private void addPage(Page pagina){

        setNewPageConfigurations(wp.getPageEvent(), pagina, wp.getPageNumber());
        setNewChapterConfigurations(wp.getPageEvent(), chapterNumber);

        try {

            for (int index = 0; index < pagina.getIndexes().size(); ++index) {
                setNewPageConfigurations(wp.getPageEvent(), pagina, wp.getPageNumber());
                try {

                    documento.add(pagina.getElement(index));

                }
                catch(NullPointerException e){
                    documento.add(PdfUtil.getErrorTable(e).getPdfTable());
                }

                addSpace();
                setNewPageConfigurations(wp.getPageEvent(), pagina, wp.getPageNumber());

            }
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }

        ++chapterNumber;
    }




    /**
     * Method to add a space between page sections
     * @throws DocumentException
     */
    private void addSpace() throws DocumentException {
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Paragraph(" ", new Font(Font.FontFamily.HELVETICA, templateConfiguration.getSectionSpacing())));
        documento.add(paragraph);
    }





    //----------------------
    //
    //----------------------



    /**
     * Metodo que permite abrir o pdf gerado
     */
    public void openPdf(Context context){

        Uri ficheiroURI;

        Intent intent = new Intent(Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            ficheiroURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", ficheiroPdf);
        }
        else{
            ficheiroURI = Uri.fromFile(ficheiroPdf);
        }

        intent.setDataAndType(ficheiroURI, PdfConstants.MIME_TYPE_APPLICATION_PDF);
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
     * Method to get the pages of the document
     * @return a list of pages
     */
    protected abstract List<Page> getPages();


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
    protected abstract void setNewPageConfigurations(PdfPageEvent pageEvent, Page chapter, int pageNumber);



}
