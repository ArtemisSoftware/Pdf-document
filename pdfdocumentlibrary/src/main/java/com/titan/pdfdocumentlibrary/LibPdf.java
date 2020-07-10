package com.titan.pdfdocumentlibrary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.titan.pdfdocumentlibrary.elements.TableWidthConverter;
import com.titan.pdfdocumentlibrary.exception.PdfLineException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LibPdf {


    public LibPdf(){};


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
    public void generatePdf(Context context, File dir){


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
            Paragraph p1 = new Paragraph("Hi! I am Generating my first PDF using PdfDocumentLibrary");
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            doc.add(p1);


            Table table_1 = new Table();
            CellConfiguration cellConfiguration_1 = new CellConfiguration();
            cellConfiguration_1.horizontalAlign = Element.ALIGN_RIGHT;
            cellConfiguration_1.verticalAlign = Element.ALIGN_MIDDLE;

            for(int aw = 0; aw < 8; aw++){
                table_1.addCell("Table 1", cellConfiguration_1);
            }

            doc.add(table_1.getPdfTable());

            doc.add(mainSection().getPdfTable());

            doc.add(noBorderSection().getPdfTable());


            doc.add(unevenSection().getPdfTable());

            doc.add(emptyCellsSection().getPdfTable());


            doc.add(emptyCellsSection().getPdfTable());

            doc.add(imageCellsSection(context).getPdfTable());

            doc.add(tableCellsSection().getPdfTable());

            doc.add(unevenTableCellsSection().getPdfTable());
            doc.add(unevenSecondTableCellsSection().getPdfTable());
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




    private Table mainSection(){

        Table colorTable = new Table(2);

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.backgroundColor = BaseColor.BLUE;


        Phrase phrase = new Phrase("Painting colors");
        colorTable.addCell(phrase, cellConfiguration);
        colorTable.addCell(phrase, cellConfiguration);


        CellConfiguration cellConfiguration_2 = new CellConfiguration();
        cellConfiguration_2.horizontalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration_2.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration_2.backgroundColor = BaseColor.MAGENTA;
        cellConfiguration_2.colSpan = 2;

        colorTable.addCell(phrase, cellConfiguration_2);

        return colorTable;
    }


    private Table noBorderSection(){

        Table table = new Table();

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.backgroundColor = BaseColor.ORANGE;


        table.addCell(new Phrase("no border table"), cellConfiguration);
        table.removeBorder();
        return table;
    }


    private Table unevenSection(){

        Table table = new Table(3);

        CellConfiguration cellConfiguration_1 = new CellConfiguration();
        cellConfiguration_1.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration_1.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration_1.backgroundColor = BaseColor.GREEN;

        CellConfiguration cellConfiguration_2 = new CellConfiguration();
        cellConfiguration_2.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration_2.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration_2.backgroundColor = BaseColor.YELLOW;

        CellConfiguration cellConfiguration [] = new CellConfiguration[]{cellConfiguration_1, cellConfiguration_2};

        Phrase phrases [] = new Phrase[]{ new Phrase("1 uneven phrase"), new Phrase("2 uneven phrase") };

        try {
            table.addLine(phrases, cellConfiguration[0]);
            table.addLine(phrases[0], cellConfiguration[1]);

            Phrase phrases_2[] = new Phrase[]{new Phrase("11 uneven phrase"), new Phrase("12 uneven phrase"), new Phrase("13 uneven phrase")};
            table.addLine(phrases_2, cellConfiguration);
        }
        catch (PdfLineException e){
            e.printStackTrace();
        }

        return table;
    }


    private Table emptyCellsSection(){

        Table table = new Table(3);

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.backgroundColor = BaseColor.DARK_GRAY;


        table.addEmptyCell();
        table.addLine(new Phrase("no border table"), cellConfiguration);

        try {
            table.addEmptyCell(2);
            table.addCell(new Phrase("no border table"), cellConfiguration);
        }
        catch (PdfLineException e){
            e.printStackTrace();
        }

        return table;
    }

    private Table imageCellsSection(Context context){

        Table table = new Table(3);

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;


        table.addCell(context.getResources(), R.drawable.tst_image);
        table.addCell(context.getResources(), R.drawable.tst_image);
        table.addCell(context.getResources(), R.drawable.tst_image);



        return table;
    }

    private Table tableCellsSection(){


        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;


        Table table1 = new Table(2);
        table1.addCell("table-1");
        table1.addCell("table-1-cellConfiguration", cellConfiguration);


        CellConfiguration cellConfiguration2 = new CellConfiguration();
        cellConfiguration2.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration2.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration2.backgroundColor = BaseColor.ORANGE;

        Table table2 = new Table(3);
        table2.addLine(new Phrase("table-2"),cellConfiguration2);
        table2.addCell("table-2-nothing");
        table2.addCell("table-2-cellConfiguration", cellConfiguration);
        table2.addCell("table-2-cellConfiguration2", cellConfiguration2);

        Table table = new Table(3);


        CellConfiguration cellConfiguration3 = new CellConfiguration();
        cellConfiguration3.backgroundColor = BaseColor.PINK;

        table.addCell(table1, cellConfiguration3);
        table.addCell(table2);
        table.addCell(table1, cellConfiguration);


        return table;
    }


    private Table unevenTableCellsSection(){

        float widhts [] = new float[]{1.5f, 5f, 5f};


        TableWidthConverter converter1 = new TableWidthConverter() {
            @Override
            public float[] convert(float[] widths) {

                float [] medidasConvertidas = new float[widths.length];

                for (int i = 0; i < widths.length; ++i) {
                    medidasConvertidas[i] = (float) (1 * widths [i]);
                }

                return medidasConvertidas;
            }
        };

        Table table = new Table(widhts/*, converter1*/);

        table.addCell("unevenTableCellsSection");
        table.addCell("unevenTableCellsSection");
        table.addCell("unevenTableCellsSection");

        return table;
    }

    private Table unevenSecondTableCellsSection(){

        float widhts [] = new float[]{150f, 15f, 300f};


        TableWidthConverter converter1 = new TableWidthConverter() {
            @Override
            public float[] convert(float[] widths) {

                float [] medidasConvertidas = new float[widths.length];

                for (int i = 0; i < widths.length; ++i) {
                    medidasConvertidas[i] = (float) (2 * widths [i]);
                }

                return medidasConvertidas;
            }
        };

        Table table = new Table(widhts/*, converter1*/);

        table.addCell("unevenSecondTableCellsSection");
        table.addCell("unevenSecondTableCellsSection");
        table.addCell("unevenSecondTableCellsSection");

        return table;
    }
}
