package com.titan.pdfdocumentlibrary.bundle;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.titan.pdfdocumentlibrary.util.PdfConstants;
import com.titan.pdfdocumentlibrary.util.PdfUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public abstract class Template {


    protected File ficheiroPdf;
    protected Document documento;
    protected PdfWriter wp;


    protected final File DIRECTORY;


    private int paginas = 0;
    //--private HashMap<Integer, Integer> paginacao;

    private List<Page> pages;


    public Template(File directory){

        DIRECTORY = directory;

        ficheiroPdf = null;
        documento = new Document();

        //--paginacao = new HashMap<Integer, Integer>();
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

        //--CabecalhoRodape evento = new CabecalhoRodape();
        //--fixarConteudoRodape(evento);

        //--documento.setPageSize(PageSize.A4);
        //--documento.setMargins(MARGEM_ESQUERDA, MARGEM_DIREITA, MARGEM_TOPO /*+ evento.obterAlturaCabecalho()*/, MARGEM_BASE);

        try {

            FileOutputStream fOut = new FileOutputStream(ficheiroPdf);
            wp = PdfWriter.getInstance(documento, fOut);

            //adicionar o evento

            //--wp.setPageEvent(evento);

            //abrir documento
            documento.open();

            for (Page page: pages) {
                addPage(page);
            }

            //--MetodosPdf.adicionarMetaDados(documento, this.getClass().getName());

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

        if(paginas != 0){

            //nova página

            documento.newPage();
            //--alterarEventoPagina(pagina, executarEventoPagina(pagina, wp.getPageNumber()));
        }

        try {
            try {
                for (int index = 0; index < pagina.getIndexes().size(); ++index) {

                    documento.add(pagina.getElement(index));

                    //--alterarEventoPagina(pagina, executarEventoPagina(pagina, wp.getPageNumber()));

                    //--documento.add(obterQuebraLinha(1, ALTURA____ENTRE_SECCOES));

                }
            }
            catch(NullPointerException e){
                documento.add(PdfUtil.getErrorTable(e).getPdfTable());
            }
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }

        ++paginas;
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
     * Metodo que permite fixar informacao ao rodape
     */
    //--abstract protected void fixarConteudoRodape(CabecalhoRodape evento);



}
