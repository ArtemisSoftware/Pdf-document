package com.titan.pdfdocumentlibrary.util;

import android.os.Environment;

import com.titan.pdfdocumentlibrary.bundle.Template;

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




}
