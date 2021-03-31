package com.titan.pdfdocumentlibrary.util;

import java.util.ArrayList;
import java.util.List;

public class PdfReport {

    public List<String> report;
    public boolean errorCreating = false, errorOpening = false;

    public PdfReport(){
        report = new ArrayList<>();
    }
}
