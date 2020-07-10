package com.titan.pdfdocument.pages.events;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PageHeaderfooter extends PdfPageEventHelper {

//    private Cabecalho cabecalho;
//    private Rodape rodape;
//    private boolean ativarCabecalho;
//    private PdfTemplate template;
//    private Image total;

    public PageHeaderfooter(){

//        cabecalho = new Cabecalho();
//        rodape = new Rodape();
//        ativarCabecalho =  false;

    }


    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        super.onOpenDocument(writer, document);
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        super.onEndPage(writer, document);
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        super.onCloseDocument(writer, document);
    }
}
