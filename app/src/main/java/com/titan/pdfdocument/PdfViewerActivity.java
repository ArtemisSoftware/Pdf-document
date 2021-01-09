package com.titan.pdfdocument;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.button.MaterialButton;

public class PdfViewerActivity extends AppCompatActivity {

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);



        pdfView = ((PDFView)findViewById(R.id.pdfView));

        Uri uri =  Uri.parse( "http://www.africau.edu/images/default/sample.pdf" );
        pdfView.fromUri(uri)
                .load();
    }
}