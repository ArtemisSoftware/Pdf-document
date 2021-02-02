package com.titan.pdfdocument;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PdfViewerActivity extends AppCompatActivity implements View.OnClickListener {

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);


        pdfView = ((PDFView)findViewById(R.id.pdfView));

        ((FloatingActionButton)findViewById(R.id.fab_big_document)).setOnClickListener(this);
        ((FloatingActionButton)findViewById(R.id.fab_small_document)).setOnClickListener(this);

        getBigDocument();

    }


    private void getSmallDocument(){

        String example = getString(R.string.example_1);

        byte[] encodedBytes = Base64.decode(example, Base64.CRLF);
        pdfView.fromBytes(encodedBytes)
                .swipeHorizontal(true)
                 .spacing(0)
                .load();


    }


    private void getBigDocument(){

        try {
            JSONObject example = new JSONObject(getTermsString());

            byte[] encodedBytes = Base64.decode(example.getString("document"), Base64.CRLF);
            pdfView.fromBytes(encodedBytes)

                    .enableSwipe(true)
                    .scrollHandle(new DefaultScrollHandle(this))
                    .spacing(2)
                    .onPageChange(new OnPageChangeListener() {
                        @Override
                        public void onPageChanged(int page, int pageCount) {

                            int lolo = page;
                        }
                    })
                    .load();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private String getTermsString() {
        StringBuilder termsString = new StringBuilder();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("pdf_cgd.txt")));

            String str;
            while ((str = reader.readLine()) != null) {
                termsString.append(str);
            }

            reader.close();
            return termsString.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.fab_big_document:

                getBigDocument();
                break;


            case R.id.fab_small_document:

                getSmallDocument();
                break;

        }

        ((FloatingActionMenu)findViewById(R.id.menu)).close(true);

    }
}