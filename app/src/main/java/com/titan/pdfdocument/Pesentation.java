package com.titan.pdfdocument;

import android.content.Context;

import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.titan.pdfdocument.pages.PresentationPage;
import com.titan.pdfdocument.pages.SecondPage;
import com.titan.pdfdocument.pages.events.PageHeaderfooter;
import com.titan.pdfdocumentlibrary.bundle.Page;
import com.titan.pdfdocumentlibrary.bundle.Template;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Pesentation extends Template {

    public Pesentation(Context context, File directory) {
        super(context, directory);
    }

    @Override
    protected String getFileName() {
        return "one";
    }

    @Override
    protected List<Page> getPages() {

        List<Page> pages = new ArrayList<>();
        pages.add(new PresentationPage());
        pages.add(new SecondPage(context));
        pages.add(new SecondPage(context));
        pages.add(new PresentationPage());
        return pages;
    }

    @Override
    protected PdfPageEventHelper getPageEvent() {
        return new PageHeaderfooter();
    }



    /**
     * Metodo que permite alterar os eventos da pagina
     * @param pageEvent
     * @param pagina a pagina a alterar
     * @param executar true caso seja para executar a alterarcao ou false caso contrario
     */
    protected void alterarEventoPagina(PdfPageEvent pageEvent, Page pagina, boolean executar){


        if(pagina.PAGE_ID == 1) {

            documento.setMargins(templateConfiguration.getLeftMargin(), templateConfiguration.getRightMargin(), templateConfiguration.getTopMargin() +/*20*/0/*((CabecalhoRodape)wp.getPageEvent()).obterAlturaCabecalho()*/, templateConfiguration.getBaseMargin());
        }
        else{
            documento.setMargins(templateConfiguration.getLeftMargin(), templateConfiguration.getRightMargin(), templateConfiguration.getTopMargin() +/*20*/0/*((CabecalhoRodape)wp.getPageEvent()).obterAlturaCabecalho()*/, templateConfiguration.getBaseMargin());

        }

//        switch (pagina.ID_PAGINA) {
//
//            case Pagina.ID_PAGINA_REGISTO_VISITA:
//
//                if(executar == true){
//
//                    //((CabecalhoRodape)wp.getPageEvent()).fixarConteudo(obterTabelaResumoCabecalho());
//                    documento.setMargins(MARGEM_ESQUERDA, MARGEM_DIREITA, MARGEM_TOPO + ((CabecalhoRodape)wp.getPageEvent()).obterAlturaCabecalho(), MARGEM_BASE);
//                }
//                break;
//
//
//            default:
//
//                ((CabecalhoRodape)wp.getPageEvent()).removerCabecalho();
//                documento.setMargins(MARGEM_ESQUERDA, MARGEM_DIREITA, MARGEM_TOPO + ((CabecalhoRodape)wp.getPageEvent()).obterAlturaCabecalho(), MARGEM_BASE);
//                break;
//        }
    }



}
