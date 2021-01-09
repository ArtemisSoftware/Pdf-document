package com.titan.pdfdocument;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PdfViewerActivity extends AppCompatActivity {

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);


        pdfView = ((PDFView)findViewById(R.id.pdfView));

        getBigDocument();
    }


    private void getSmallDocument(){

        String example = getString(R.string.example_1);

        byte[] encodedBytes = Base64.decode(example, Base64.CRLF);
        pdfView.fromBytes(encodedBytes)
                .swipeHorizontal(true)
                .spacing(10)
                .load();


    }


    private void getBigDocument(){

        try {
            JSONObject example_ = new JSONObject(getTermsString());

            byte[] encodedBytes = Base64.decode(example_.getString("document"), Base64.CRLF);
            pdfView.fromBytes(encodedBytes)
                    .swipeHorizontal(true)
                    .spacing(10)
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

}