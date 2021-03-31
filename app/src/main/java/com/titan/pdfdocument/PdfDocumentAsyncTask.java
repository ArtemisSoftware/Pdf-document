package com.titan.pdfdocument;

import android.content.Context;
import android.os.AsyncTask;

import com.titan.pdfdocumentlibrary.PdfReportListener;
import com.titan.pdfdocumentlibrary.bundle.Template;

public class PdfDocumentAsyncTask extends AsyncTask<Void, Void, Void> {

    private Context mContext;
    private Template template;
    private PdfReportListener listener;

    public PdfDocumentAsyncTask(Context context, Template template, PdfReportListener listener){
        mContext = context;
        this.template = template;
        this.listener = listener;
    }


    @Override
    protected Void doInBackground(Void... voids) {

        this.template.createFile();

        return null;
    }


    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        this.template.openPdf();
        this.listener.pdfReport(template.pdfReport.report);
    }
}
