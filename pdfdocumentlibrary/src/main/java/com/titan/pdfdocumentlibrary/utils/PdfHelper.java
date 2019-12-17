package com.titan.pdfdocumentlibrary.utils;

import com.itextpdf.text.Document;

public class PdfHelper {


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
