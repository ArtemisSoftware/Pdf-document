package com.titan.pdfdocumentlibrary.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Environment;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.titan.pdfdocumentlibrary.bundle.Template;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PdfUtil {


    /**
     * Method that generates a pdf file<br>
     * If a file already exists it will delete it first
     * @param template the class that is generating the file
     * @param directory the directory where the file will be created
     * @param name the name of the file
     * @return an empty file
     */
    public static File getFile(Template template, File directory, String name) {

        name = template.getClass().getSimpleName() + "__" + name + PdfConstants.PDF_EXTENSION;

        deleteFile(directory, name);

        return new File(directory, name);
    }


    /**
     * Method that creates an error table with an exception
     * @param exception the exception to present on the table
     * @return an error table
     */
    public static Table getErrorTable(Exception exception){


        CellConfiguration cellConfigurationTitle = new CellConfiguration();
        cellConfigurationTitle.horizontalAlign = Element.ALIGN_LEFT;
        cellConfigurationTitle.backgroundColor = BaseColor.RED;

        Table table = new Table();
        table.addCell("ERROR", cellConfigurationTitle);
        table.addCell(exception.getMessage());

/*
        table.addCell(MetodosLog.formatarExcecao(excepcao));
*/
        table.setBorderColor(BaseColor.RED);
        return table;

    }



    /**
     * Method to add metadata to the document
     * @param context the app context
     * @param document the document
     * @param template the class that is generating the file
     */
    public static void addMetaData(Context context, Document document, Template template) {

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(currentTime);


        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        String appName =  stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);

        document.addTitle(template.getClass().getSimpleName());
        document.addSubject("Date: "+  formattedDate);
        document.addCreator(appName);
    }


    /**
     * Method to delete all pdf files from a directory
     * @param directory the directory where the files are located
     */
    public static void deleteAllFiles(File directory){

        File[] Files = directory.listFiles();

        if(Files != null) {
            int j;
            for(j = 0; j < Files.length; j++) {

                if(Files[j].getAbsolutePath().contains(PdfConstants.PDF_EXTENSION) == true){
                    Files[j].delete();
                }
            }
        }
    }


    /**
     * Method to delete a specific pdf file from a directory
     * @param directory the directory where the file is located
     * @param fileName the name of the file
     */
    public static void deleteFile(File directory, String fileName){

        File[] Files = directory.listFiles();

        if(Files != null) {
            int j;
            for(j = 0; j < Files.length; j++) {

                if(Files[j].getAbsolutePath().contains(PdfConstants.PDF_EXTENSION) == true && Files[j].getAbsolutePath().contains(fileName) == true){
                    Files[j].delete();
                }
            }
        }
    }

}
