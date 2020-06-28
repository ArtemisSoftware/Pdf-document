package com.titan.pdfdocumentlibrary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.titan.pdfdocumentlibrary.utils.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class PDFDocument {


    protected Context contexto;
    protected File ficheiroPdf;
    protected Document documento;
    protected PdfWriter wp;


    protected final String DIRETORIA;

    public PDFDocument(Context contexto){

        this.contexto = contexto;
        DIRETORIA = Environment.getExternalStorageDirectory().getAbsolutePath() +"/" /*+ AppConfigIF.DIRETORIA_PDF*/;

        ficheiroPdf = null;
        documento = new Document();

        //gerarFontes();

    }


    /**
     * Metodo que permite abrir o pdf gerado
     */
    public void abrirPdf(){

        Intent intent = new Intent(Intent.ACTION_VIEW);
        //intent.setDataAndType(Uri.fromFile(ficheiroPdf), AppConfigIF.MIME_TIPO_APLICACAO_PDF);
        contexto.startActivity(intent);
    }


    /**
     * Metodo que permite abrir um pdf
     * @param contexto
     * @param caminho o caminho para o pdf
     */
    public static void abrirPdf(Context contexto, String caminho){

        File ficheiro = new File(caminho);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        //intent.setDataAndType(Uri.fromFile(ficheiro), AppConfigIF.MIME_TIPO_APLICACAO_PDF);
        contexto.startActivity(intent);

    }


    /**
     * Metodo que gera o pdf
     */
    public void gerarDocumento() {

        //ficheiroPdf = MetodosPdf.gerarFicheiro(this.getClass().getSimpleName(), DIRETORIA,  obterNomeFicheiro());
        criarDocumento();
    }





    /**
     * MEtodo que cria um ficheiro pdf representativo do acordo
     */
    private void criarDocumento(){

        //CabecalhoRodape evento = new CabecalhoRodape();
        //fixarConteudoRodape(evento);

        documento.setPageSize(PageSize.A4);
        //documento.setMargins(MARGEM_ESQUERDA, MARGEM_DIREITA, MARGEM_TOPO, MARGEM_BASE);


        try {

            FileOutputStream fOut = new FileOutputStream(ficheiroPdf);
            wp= PdfWriter.getInstance(documento, fOut);

            //adicionar o evento

            //LogApp_v3.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Adicionado um evento ao documento");
            //wp.setPageEvent(evento);

            //abrir documento
            documento.open();

            //LogApp_v3.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("A iniciar a geração de conteudo do pdf...");

            gerarConteudoDocumento();

            //LogApp_v3.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Concluida a geração de conteudo do pdf.");

            //MetodosPdf.adicionarMetaDados(documento, this.getClass().getName());

            //LogApp_v3.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Pdf criado com sucesso");

        }
        catch (DocumentException | IOException de) {
            /*
            Log.e("PDFCreator", "Exception:" + de);

            LogApp_v3.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Erro ao criar o pdf");
            LogApp_v3.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarExcecaoErro("Geração do pdf", de);
            */
        }
        catch (Exception de) {
            /*
            Log.e("PDFCreator", "Exception:" + de);

            LogApp_v3.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Erro ao criar o pdf");
            LogApp_v3.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarExcecaoErro("Geração do pdf", de);
            */
        }

        finally{
            documento.close();
        }
    }



    /**
     * Metodo que permite obter o caminho do ficheiro
     * @return um caminho
     */
    public String obterCaminho() {
        return ficheiroPdf.getAbsolutePath();
    }




    //------------------------------------
    //Metodos abstratos
    //------------------------------------


    /**
     * Metodo que permite obter o nome do ficheiro
     * @return o nome do ficheiro
     */
    protected abstract String obterNomeFicheiro();


    /**
     * Metodo que gera o conteudo do documento
     */
    abstract protected void gerarConteudoDocumento();


    /**
     * Metodo que permite fixar informacao ao rodape
     */
    //abstract protected void fixarConteudoRodape(CabecalhoRodape evento);

    //-----------------------
    //teste
    //-----------------------



    /**
     * MEtodo que abre um pdf de teste
     */
    public void abrirPdfTeste(){

        Intent intent = new Intent(Intent.ACTION_VIEW);

        File file = new File(DIRETORIA, Constants.DEMO_PDF);

        intent.setDataAndType(Uri.fromFile(file),  Constants.TYPE_PDF);
        contexto.startActivity(intent);
    }


    /**
     * MEtodo que gera um pdf de teste
     */
    public void gerarPdfTeste(){

        Document doc = new Document();

        try {

            File dir = new File(DIRETORIA);
            if(!dir.exists())
                dir.mkdirs();

            //Log.d("PDFCreator", "PDF Path: " + DIRETORIA);

            File file = new File(dir, "demo.pdf");
            FileOutputStream fOut = new FileOutputStream(file);

            PdfWriter.getInstance(doc, fOut);

            //PdfWriter writer = PdfWriter.getInstance(doc, fOut);
            //--Rodape eventoRodape = new Rodape(MetodosDatas.obterDataHoje(DatasIF.DATA_FORMATO_DD_MM_YYYY__HH_MM_SS));
            //writer.setPageEvent(eventoRodape);

//	        MyFooter event = new MyFooter();
            //writer.setPageEvent(event);


            //open the document
            doc.open();

            /* Create Paragraph and Set Font */
            Paragraph p1 = new Paragraph("Hi! I am Generating my first PDF using DroidText");
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            doc.add(p1);


        }
        catch (DocumentException de) {
            //Log.e("PDFCreator", "DocumentException:" + de);
        }
        catch (IOException e) {
            //Log.e("PDFCreator", "ioException:" + e);
        }
        finally{
            doc.close();
        }
    }

}
