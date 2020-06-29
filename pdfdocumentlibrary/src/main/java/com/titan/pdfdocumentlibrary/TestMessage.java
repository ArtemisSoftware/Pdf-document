package com.titan.pdfdocumentlibrary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestMessage {

    public static void test(Context context, String message){

        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();

    }




    public void lolo (Context context, File dir){

        gerarPdfTeste(dir);
        abrirPdfTeste(context);
    }


    /**
     * MEtodo que abre um pdf de teste
     */
    public void abrirPdfTeste(Context context){


        String diretoriaContratos = Environment.getExternalStorageDirectory().getAbsolutePath() +"/" + "pdfs";//AppIF.DIRETORIA_CONTRATOS;

        Intent intent = new Intent(Intent.ACTION_VIEW);

        File file = new File(diretoriaContratos, "demo.pdf");


        Uri photoURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);



        intent.setDataAndType(photoURI, "application/pdf" );
        context.startActivity(intent);
    }


    /**
     * MEtodo que gera um pdf de teste
     */
    public void gerarPdfTeste(File dir){


        Document doc = new Document();


        try {


            //Log.d("PDFCreator", "PDF Path: " + diretoriaContratos);

            File file = new File(dir, "demo.pdf");
            FileOutputStream fOut = new FileOutputStream(file);

            //PdfWriter.getInstance(doc, fOut);

            PdfWriter writer = PdfWriter.getInstance(doc, fOut);
//            Rodape eventoRodape = new Rodape(MetodosDatas.obterDataHoje(DatasIF.DATA_FORMATO_DD_MM_YYYY__HH_MM_SS));
//            writer.setPageEvent(eventoRodape);

//	        MyFooter event = new MyFooter();
            //writer.setPageEvent(event);


            //open the document
            doc.open();

            /* Create Paragraph and Set Font */
            Paragraph p1 = new Paragraph("Hi! I am Generating my first PDF using DroidText");
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            doc.add(p1);


            PdfPTable table = new PdfPTable(8);
            for(int aw = 0; aw < 16; aw++){
                table.addCell("hi");
            }
            doc.add(table);


        }
        catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        }
        catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        }
        finally{
            doc.close();
        }
    }







}
