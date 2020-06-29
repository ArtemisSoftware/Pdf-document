package com.titan.pdfdocument;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.titan.pdfdocumentlibrary.TestMessage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestMessage.test(this, "ddd-lolo");
        TestMessage lolo = new TestMessage();


        String diretoriaContratos = Environment.getExternalStorageDirectory().getAbsolutePath() +"/" + "pdfs";//AppIF.DIRETORIA_CONTRATOS;


        boolean created = false;

        File dir = new File(diretoriaContratos);
        if(!dir.exists())
            created = dir.mkdirs();



        lolo.lolo(this, dir);
    }


}
