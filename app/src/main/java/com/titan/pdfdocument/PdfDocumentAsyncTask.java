package com.titan.pdfdocument;

import android.content.Context;
import android.os.AsyncTask;

import com.titan.pdfdocumentlibrary.bundle.Template;

public class PdfDocumentAsyncTask extends AsyncTask<Void, Void, Void> {

    private Context mContext;
    private Template template;

    public PdfDocumentAsyncTask(Context context, Template template){
        mContext = context;
        this.template = template;
    }


    @Override
    protected Void doInBackground(Void... voids) {

        this.template.createFile();

        return null;
    }


    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        this.template.openPdf(mContext);
    }
}
