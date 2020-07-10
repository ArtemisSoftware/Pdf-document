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

public class SimplePdf {

    public static void test(Context context, String message){

        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();

    }


    /**
     * Method that allows to open a pdf test file
     * @param context the app context
     */
    public void openPdf(Context context){

        String diretoriaContratos = Environment.getExternalStorageDirectory().getAbsolutePath() +"/" + "pdfs";//AppIF.DIRETORIA_CONTRATOS;
        File file = new File(diretoriaContratos, "demo.pdf");

        Uri photoURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(photoURI, "application/pdf" );
        context.startActivity(intent);
    }


    /**
     * Method that populates a pdf with test data
     * @param dir the pdf directory
     */
    public void generatePdf(File dir){


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

            /* Create Paragraph and Set FontConfiguration */
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
