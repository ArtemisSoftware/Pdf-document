package com.titan.pdfdocument;

import android.content.Context;

import com.titan.pdfdocument.pages.PresentationPage;
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

        return pages;
    }
}