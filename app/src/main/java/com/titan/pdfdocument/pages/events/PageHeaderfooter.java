package com.titan.pdfdocument.pages.events;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import com.titan.pdfdocument.sections.FooterSection;
import com.titan.pdfdocument.sections.HeaderSection;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;

public class PageHeaderfooter extends PdfPageEventHelper {

    private HeaderSection headerSection;
    private FooterSection footerSection;

//    private boolean ativarCabecalho;

    private PdfTemplate template;
    private Image total;
    public boolean showHeader;

    public PageHeaderfooter(){

        showHeader = false;
        headerSection = new HeaderSection();
        footerSection = new FooterSection();

//        rodape = new Rodape();
//        ativarCabecalho =  false;

    }



    private void setFooter(PdfWriter writer, Document document){

        PdfContentByte cb = writer.getDirectContent();

        FontConfiguration font = new FontConfiguration();
        Phrase rodape [] = new Phrase[4];
        rodape [0] = new Phrase("First line of footer", font.getFont(8, false, BaseColor.GRAY));
        rodape [1] = new Phrase("Second line of footer", font.getFont(8, false, BaseColor.GRAY));
        rodape [2] = new Phrase("Third line of footer", font.getFont(8, false, BaseColor.GRAY));
        rodape [3] = new Phrase("Fourth line of footer", font.getFont(8, false, BaseColor.GRAY));



        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, rodape[0], document.left()/*(document.right() - document.left()) / 2 + document.leftMargin()*/, document.bottom() - 10, 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, rodape[1], document.left()/*(document.right() - document.left()) / 2 + document.leftMargin()*/, document.bottom() - 18, 0);

        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, rodape[2], document.left()/* (document.right() - document.left()) / 2 + document.leftMargin()*/, document.bottom() - 28, 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, rodape[3], document.left()/*(document.right() - document.left()) / 2 + document.leftMargin()*/, document.bottom() - 38, 0);

        //rodape.adicionarNumeroPagina(writer.getPageNumber(), total);


        PdfContentByte canvas = writer.getDirectContent();
        canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
        //rodape.obterTabelaPaginacao().obterTabela().writeSelectedRows(0, -1, 36, /*30*/25, canvas);
        canvas.endMarkedContentSequence();

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

        setFooter(writer, document);
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        super.onCloseDocument(writer, document);
    }
}
