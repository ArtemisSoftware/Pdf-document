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

    private int pageNumber;
    //--private HashMap<Integer, Integer> paginacao;

    private List<Page> pages;


    public Template(Context context, File directory){

        this.context = context;
        DIRECTORY = directory;

        ficheiroPdf = null;
        documento = new Document();
        pageNumber = 0;

        templateConfiguration = new TemplateConfiguration();

        paginacao = new HashMap<Integer, Integer>();
    }


    /**
     * Method to create the pdf file
     */
    public void createFile() {

        ficheiroPdf = PdfUtil.getFile(this, DIRECTORY, getFileName());

        pages = getPages();

        criarDocumento();
    }


    /**
     * Metodo que cria um ficheiro pdf representativo do acordo
     * param ficheiro ficheiro a ser criado
     */
    private void criarDocumento(){

        PdfPageEventHelper pageEventHelper = getPageEvent();

        //--CabecalhoRodape evento = new CabecalhoRodape();
        //--fixarConteudoRodape(evento);

        documento.setPageSize(templateConfiguration.getPageSize());
        //documento.setMargins(36, 50, 200, 70);
        //--documento.setMargins(templateConfiguration.getLeftMargin(), templateConfiguration.getRightMargin(), templateConfiguration.getTopMargin() /*+ evento.obterAlturaCabecalho()*/, templateConfiguration.getBaseMargin());

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

        if(pageNumber != 0){

            //nova página

            //--alterarEventoPagina(wp.getPageEvent(), pagina, executarEventoPagina(pagina, wp.getPageNumber()));
            documento.newPage();
        }
        if(pageNumber == 0){

            //nova página

            //--alterarEventoPagina(wp.getPageEvent(), pagina, executarEventoPagina(pagina, wp.getPageNumber()));
            documento.newPage();
        }

        try {

            for (int index = 0; index < pagina.getIndexes().size(); ++index) {

                try {
                    //--alterarEventoPagina(wp.getPageEvent(), pagina, executarEventoPagina(pagina, wp.getPageNumber()));
                    documento.add(pagina.getElement(index));

                    //alterarEventoPagina(wp.getPageEvent(), pagina, executarEventoPagina(pagina, wp.getPageNumber()));
                }
                catch(NullPointerException e){
                    documento.add(PdfUtil.getErrorTable(e).getPdfTable());
                }

                addSpace();
            }

        }
        catch (DocumentException e) {
            e.printStackTrace();
        }

        ++pageNumber;
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


    private HashMap<Integer, Integer> paginacao;


    /**
     * Metodo que indica se um evento pode ser executado na pagina corrente
     * @param pagina a pagina a ser implementada
     * @param numero o numero da pagina
     * @return true caso possa ser executado o evento ou false caso contrário
     */
    private boolean executarEventoPagina(Page pagina, int numero){

        boolean resultado = false;
  /*
        if(paginacao.containsKey(numero) == false){
            resultado = true;
            paginacao.put(numero, pagina.PAGE_ID);
        }
*/
/*

        if(paginacao.containsKey(numero) == false){
            resultado = false;
        }

        if(paginacao.containsKey(numero) == true & paginacao.containsValue(pagina.PAGE_ID) == true){
            resultado = false;
        }

        if(paginacao.containsKey(numero) == false & paginacao.containsValue(pagina.PAGE_ID) == true){
            resultado = true;
        }

        paginacao.put(numero, pagina.PAGE_ID);

 */
        return true;

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


    protected abstract void alterarEventoPagina(PdfPageEvent pageEvent, Page pagina, boolean executar);


    /**
     * Metodo que permite fixar informacao ao rodape
     */
    //--abstract protected void fixarConteudoRodape(CabecalhoRodape evento);



}
