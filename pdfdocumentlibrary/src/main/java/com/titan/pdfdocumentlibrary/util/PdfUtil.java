package com.titan.pdfdocumentlibrary.util;

import android.os.Environment;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.titan.pdfdocumentlibrary.bundle.Template;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;

import java.io.File;

public class PdfUtil {


    /**
     * Method that generates a pdf file
     * @param template the class that is generating the file
     * @param directory the directory where the file will be created
     * @param name the name of the file
     * @return an empty file
     */
    public static File getFile(Template template, File directory, String name) {

        name = template.getClass().getSimpleName() + "__" + name + PdfConstants.PDF_EXTENSION;
        return new File(directory, name);
    }


    /**
     * Method that creates an error table with an exception
     * @param index the index data
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

}
