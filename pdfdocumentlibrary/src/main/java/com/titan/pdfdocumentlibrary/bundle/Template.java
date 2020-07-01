package com.titan.pdfdocumentlibrary.bundle;

import android.util.Log;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class Template {


    protected File ficheiroPdf;
    protected Document documento;
    protected PdfWriter wp;


    protected final String DIRETORIA;


    private int paginas = 0;
    //--private HashMap<Integer, Integer> paginacao;


    public Template(String directory){

        DIRETORIA = "";//Environment.getExternalStorageDirectory().getAbsolutePath() +"/" + AppConfigIF.DIRETORIA_PDF;

        ficheiroPdf = null;
        documento = new Document();

        //--paginacao = new HashMap<Integer, Integer>();
    }


    /**
     * Metodo que gera o pdf
     */
    public void gerarDocumento() {

        //--ficheiroPdf = MetodosPdf.gerarFicheiro(this.getClass().getSimpleName(), DIRETORIA,  obterNomeFicheiro());
        criarDocumento();
    }


    /**
     * MEtodo que cria um ficheiro pdf representativo do acordo
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


            gerarConteudoDocumento();


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
    //--abstract protected void fixarConteudoRodape(CabecalhoRodape evento);



}
