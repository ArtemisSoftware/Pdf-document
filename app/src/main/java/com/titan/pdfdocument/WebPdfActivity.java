package com.titan.pdfdocument;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class WebPdfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_pdf);

        String urlBaseGoogle = "https://docs.google.com/viewer?url=";
        //String url = "https://www.google.com/";
        //String url = "https://www.zara.com/pt/";
        //String url = "http://www.columbia.edu/~fdc/sample.html";
        //String url = "http://www.africau.edu/images/default/sample.pdf";
        String url = "https://www.cgd.pt/Site/Montras-digitais/Documents/DABOX_CGU-Pagamentos.pdf";
//        String url = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";

        String page = "#page=3";


        String urlToload = urlBaseGoogle + url + page;
        WebView webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(urlToload);
        webview.setWebViewClient(new CustomWebClient((ProgressBar) findViewById(R.id.progressBar)));


//        "https://www.cgd.pt/Site/Montras-digitais/Documents/DABOX_CGU-Pagamentos.pdf"
//        http://docs.google.com/gview?embedded=true&url=" +webUrl
    }
}