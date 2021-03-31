package com.titan.pdfdocument;

import android.content.Context;
import android.os.AsyncTask;

import com.titan.pdfdocument.ui.PdfReportListener;
import com.titan.pdfdocumentlibrary.LibPdf;
import com.titan.pdfdocumentlibrary.SimplePdf;

import java.io.File;

public class TesterPdfAsyncTask extends AsyncTask<File, String, Void> {


    private Context mContext;
    //private SimplePdf testPdf;
    private LibPdf testPdf;
    private PdfReportListener listener;

    public TesterPdfAsyncTask(Context context, PdfReportListener listener){
        mContext = context;
        testPdf = new LibPdf();
        this.listener = listener;
    }


    @Override
    protected Void doInBackground(File... files) {

        File dir = files[0];
        testPdf.generatePdf(mContext, dir);

        return null;
    }



    @Override
    protected void onPostExecute(Void result) {

        testPdf.openPdf(mContext);
        this.listener.pdfReport(testPdf.report);
    }

}
