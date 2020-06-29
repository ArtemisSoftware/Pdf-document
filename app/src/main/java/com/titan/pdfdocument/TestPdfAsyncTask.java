package com.titan.pdfdocument;

import android.content.Context;
import android.os.AsyncTask;

import com.titan.pdfdocumentlibrary.LibPdf;
import com.titan.pdfdocumentlibrary.SimplePdf;

import java.io.File;

public class TestPdfAsyncTask extends AsyncTask<File, String, Void> {


    private Context mContext;
    //private SimplePdf testPdf;
    private LibPdf testPdf;

    public TestPdfAsyncTask(Context context){
        mContext = context;
        testPdf = new LibPdf();
    }


    @Override
    protected Void doInBackground(File... files) {

        File dir = files[0];
        testPdf.generatePdf(dir);

        return null;
    }



    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        testPdf.openPdf(mContext);
    }

}
