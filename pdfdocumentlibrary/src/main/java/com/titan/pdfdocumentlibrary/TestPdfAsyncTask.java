package com.titan.pdfdocumentlibrary;

import android.content.Context;
import android.os.AsyncTask;

import java.io.File;

public class TestPdfAsyncTask extends AsyncTask<File, String, Void> {


    private Context mContext;
    //private SimplePdf testPdf;
    private LibPdf testPdf;
    private PdfReportListener listener;

    public TestPdfAsyncTask(Context context){
        mContext = context;
        testPdf = new LibPdf();
    }


    @Override
    protected Void doInBackground(File... files) {

        File dir = files[0];
        testPdf.generatePdf(mContext, dir);

        return null;
    }



    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        testPdf.openPdf(mContext);
        this.listener.pdfReport(testPdf.report);
    }

}
