package com.titan.pdfdocument.pages.events;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
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

    public PageHeaderfooter(){

        headerSection = new HeaderSection();
//        rodape = new Rodape();
//        ativarCabecalho =  false;

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

        //if(ativarCabecalho == true){

        PdfPTable headerTable = headerSection.getSection().getPdfTable();
        float height = headerTable.getTotalHeight()/*38*/;

        headerTable.writeSelectedRows(0, -1, document.left(), document.top() + ((document.topMargin() + height) / 2), writer.getDirectContent());
        //}

    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        super.onCloseDocument(writer, document);
    }
}
