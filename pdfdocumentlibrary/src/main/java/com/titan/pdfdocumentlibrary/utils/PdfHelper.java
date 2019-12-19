package com.titan.pdfdocumentlibrary.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class PdfHelper {


    /**
     * Metodo que gera um ficheiro pdf
     * @param diretoria diretoria onde colocar o ficheiro
     * @param nome o nome do ficheiro que será adicionado ao titulo
     * @return um ficheiro vazio
     */
    public static File gerarFicheiro(String diretoria, String nome){

        nome = nome + Constants.PDF_FILE_EXTENSION;

        //LogApp_v3.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Nome PDF: " + nome);

        File diretoriaFicheiros = new File(diretoria);
        File ficheiro = new File(diretoriaFicheiros, nome);

        //LogApp_v3.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Caminho PDF : " + ficheiro.getAbsolutePath());
        return ficheiro;
    }


    /**
     * Metodo que permite adicionar metadados a um documento
     * @param documento o documento
     * @param nome o nome do ficheiro
     * @param subject
     * @param creator
     */
    public static void adicionarMetaDados(Document documento, String nome, String subject, String creator) {

        documento.addTitle(nome);
        documento.addSubject(subject);
        documento.addCreator(creator);
    }


    /**
     * Metodo que permite formatar imagens para serem colocadas num pdf
     * @param recursos recursos da aplicacao
     * @param imagem imagem escolhida
     * @return uma imagem formatada
     */
    public static Image imagemPdf(Resources recursos, int imagem) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(recursos, imagem);

        try{

            bitmap.compress(Bitmap.CompressFormat.PNG, 100 , stream);
        }
        catch(Exception e){
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , stream);
        }

        Image imagemPDF = null;

        try {
            imagemPDF = Image.getInstance(stream.toByteArray());
        }
        catch (BadElementException | IOException e) {
            e.printStackTrace();
        }

        return imagemPDF;
    }




    /**
     * Metodo que permite converter medidas em centimetros para floats
     * @param medidas lista de medidas a converter
     * @return uma lista de medidas convertidas
     */
    public static float [] converterDimensoes(float [] medidas, double razao){

        float [] medidasConvertidas = new float[medidas.length];

        for (int i = 0; i < medidas.length; ++i) {
            medidasConvertidas[i] = (float) (razao * medidas [i]);
        }

        return medidasConvertidas;
    }
}
