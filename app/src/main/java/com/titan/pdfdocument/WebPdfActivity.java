package com.titan.pdfdocument;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebPdfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_pdf);

        String urlBaseGoogle = "https://docs.google.com/viewer?url=";
        String url = "https://www.google.com/";
//        String url = "http://www.africau.edu/images/default/sample.pdf";
//        String url = "https://www.cgd.pt/Site/Montras-digitais/Documents/DABOX_CGU-Pagamentos.pdf";
//        String url = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";

        String urlToload = urlBaseGoogle + url;
        WebView webview = (WebView) findViewById(R.id.webview);
        webview.loadUrl(urlToload);



//        "https://www.cgd.pt/Site/Montras-digitais/Documents/DABOX_CGU-Pagamentos.pdf"
//        http://docs.google.com/gview?embedded=true&url=" +webUrl
    }
}