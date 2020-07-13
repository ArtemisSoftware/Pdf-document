package com.titan.pdfdocument.pages.events;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import com.titan.pdfdocument.sections.HeaderSection;

public class PageHeaderfooter extends PdfPageEventHelper {

    private HeaderSection headerSection;
//    private Rodape rodape;
//    private boolean ativarCabecalho;

    private PdfTemplate template;
    private Image total;
    public boolean showHeader;

    public PageHeaderfooter(){

        showHeader = false;
        headerSection = new HeaderSection();

//        rodape = new Rodape();
//        ativarCabecalho =  false;

    }


    public void fixarConteudo(){

        //cabecalho = new Cabecalho();
        showHeader = true;
    }

    public void removerCabecalho(){

        //cabecalho = new Cabecalho();
        showHeader = false;
    }


    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        template = writer.getDirectContent().createTemplate(30, 16);
        try {
            total = Image.getInstance(template);
            total.setRole(PdfName.ARTIFACT);
        }
        catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }



    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        //header


//        if(document.getPageNumber() == 1 /*|| document.getPageNumber() == 4*/){
//            document.setMargins(36, 50, 200, 70);
//        }
//
//
//        if(document.getPageNumber() == 2 /*|| document.getPageNumber() == 4*/){
//            document.setMargins(36, 50, 20, 70);
//        }
//
//
//        if(document.getPageNumber() == 3 /*|| document.getPageNumber() == 4*/){
//            document.setMargins(36, 50, 40, 70);
//        }
//
//
//        if(document.getPageNumber() == 4 /*|| document.getPageNumber() == 4*/){
//            document.setMargins(36, 50, 80, 70);
//        }


//
//        if(document.getPageNumber() == 1 || document.getPageNumber() == 4/*showHeader == true*/){
//
//        PdfPTable headerTable = headerSection.getSection().getPdfTable();
//        float height = /*headerTable.getTotalHeight()*/0;
//
//            document.setMargins(36, 50, 100/*((CabecalhoRodape)wp.getPageEvent()).obterAlturaCabecalho()*/, 70);
//        headerTable.writeSelectedRows(0, -1, document.left(), document.top() + ((document.topMargin() + height) / 2), writer.getDirectContent());
//        }


//        else if(document.getPageNumber() == 2 || document.getPageNumber() == 3 || document.getPageNumber() == 5){
//            document.setMargins(36, 50, 100 -80/*((CabecalhoRodape)wp.getPageEvent()).obterAlturaCabecalho()*/, 70);
//
//        }
//        else{
//            document.setMargins(36, 50, 100/*((CabecalhoRodape)wp.getPageEvent()).obterAlturaCabecalho()*/, 70);
//        }
//
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        super.onCloseDocument(writer, document);
    }
}
