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

import java.util.HashMap;

public class PageHeaderfooter extends PdfPageEventHelper {

    private HeaderSection headerSection;
    private FooterSection footerSection;

    private HashMap<Integer, Integer> pagination;

    private PdfTemplate template;
    private Image total;


    public PageHeaderfooter(){

        headerSection = new HeaderSection();
        footerSection = new FooterSection();
    }


    public void setRelations(HashMap<Integer, Integer> pagination) {
        this.pagination = pagination;
    }


    private void setHeader(PdfWriter writer, Document document){

        PdfPTable headerTable = headerSection.getSection().getPdfTable();
        float height = /*headerTable.getTotalHeight()*/0;



        if(this.pagination == null){
            return;
        }


        int chapterId = 0;

        try {

            chapterId = this.pagination.get(document.getPageNumber());

        }
        catch (NullPointerException e) {

            chapterId = this.pagination.get(this.pagination.size());
        }


        switch (chapterId) {

            case 1:
            case 3:

                headerTable.writeSelectedRows(0, -1, document.left(), document.top() + ((document.topMargin() + height) / 2), writer.getDirectContent());
                break;

            default:
                break;

        }
//try {
//    if (this.pagination.get(document.getPageNumber()) == 3 || this.pagination.get(document.getPageNumber()) == 1) {
//
//        headerTable.writeSelectedRows(0, -1, document.left(), document.top() + ((document.topMargin() + height) / 2), writer.getDirectContent());
//        //document.setMargins(36, 50, 200, 100);
//    }
//}
//catch (NullPointerException e){
//
//    try {
//
//    if (this.pagination.get(document.getPageNumber() -1 ) == 3) {
//
//        headerTable.writeSelectedRows(0, -1, document.left(), document.top() + ((document.topMargin() + height) / 2), writer.getDirectContent());
//        //document.setMargins(36, 50, 0, 100);
//    }
//
//    }
//    catch (NullPointerException y){
//
//        //document.setMargins(36, 50, 00, 100);
//    }
//}
    }



    private void setFooter(PdfWriter writer, Document document){

        PdfContentByte cb = writer.getDirectContent();

/*
        PdfContentByte canvasReferencia = writer.getDirectContent();
        canvasReferencia.beginMarkedContentSequence(PdfName.ARTIFACT);
        footerSection.obterTabelaReferencia().obterTabela().writeSelectedRows(0, -1, 15.7f* 36, 75, canvasReferencia);
        canvasReferencia.endMarkedContentSequence();
*/

        PdfContentByte canvas = writer.getDirectContent();
        canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
        footerSection.addPageNumberFooter(writer.getPageNumber(), total);
        footerSection.getSection().getPdfTable().writeSelectedRows(0, -1, 36, /*30*/90, canvas);
        canvas.endMarkedContentSequence();

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

        setHeader(writer, document);
        setFooter(writer, document);
    }



    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {

        FontConfiguration font = new FontConfiguration();
        ColumnText.showTextAligned(template, Element.ALIGN_LEFT, new Phrase(String.valueOf(writer.getPageNumber()/* -1*/), font.getFont(7+6,true, BaseColor.GRAY)), 2, /*4*/3.6f, 0);
    }


}
