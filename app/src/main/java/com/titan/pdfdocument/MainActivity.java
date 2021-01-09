package com.titan.pdfdocument;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.titan.pdfdocumentlibrary.bundle.Template;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SimplePdf.test(this, "ddd-lolo");
        //--requestStoragePermission();

        ((MaterialButton)findViewById(R.id.btn_test_pdf)).setOnClickListener(btn_test_pdf__OnClickListener);
        ((MaterialButton)findViewById(R.id.btn_presentation_pdf)).setOnClickListener(btn_presentation_pdf__OnClickListener);
        ((MaterialButton)findViewById(R.id.btn_test_pdf_web)).setOnClickListener(btn_web_pdf__OnClickListener);

        ((MaterialButton)findViewById(R.id.btn_test_pdf_viewer)).setOnClickListener(this);



    }


    private void presentationPdf(){
        String directoryPath = Environment.getExternalStorageDirectory().getAbsolutePath() +"/" + "pdfs";//AppIF.DIRETORIA_CONTRATOS;


        boolean created = false;

        File dir = new File(directoryPath);
        if(!dir.exists())
            created = dir.mkdirs();


        Template presentation = new Pesentation(this, dir);
        PdfDocumentAsyncTask taskPresentation = new PdfDocumentAsyncTask(this, presentation);
        taskPresentation.execute();
    }


    private void testPdf(){
        String directoryPath = Environment.getExternalStorageDirectory().getAbsolutePath() +"/" + "pdfs";//AppIF.DIRETORIA_CONTRATOS;


        boolean created = false;

        File dir = new File(directoryPath);
        if(!dir.exists())
            created = dir.mkdirs();


        TesterPdfAsyncTask task = new TesterPdfAsyncTask(this);
        task.execute(dir);

    }




    MaterialButton.OnClickListener btn_presentation_pdf__OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(checkPdfPermissions() == true) {
                presentationPdf();
            }
        }
    };


    MaterialButton.OnClickListener btn_test_pdf__OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(checkPdfPermissions() == true) {
                testPdf();
            }

        }
    };



    MaterialButton.OnClickListener btn_web_pdf__OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getApplicationContext(), WebPdfActivity.class);
            startActivity(intent);

        }
    };



    private boolean checkPdfPermissions(){

        int storage = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);

        if (storage == PackageManager.PERMISSION_GRANTED && read == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else{
            requestStoragePermission();
            return false;
        }
    }



    /**
     * Requesting multiple permissions (storage and location) at once
     * This uses multiple permission model from dexter
     * On permanent denial opens settings dialog
     */
    private void requestStoragePermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted!", Toast.LENGTH_SHORT).show();

                            //presentationPdf();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }




    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_test_pdf_viewer:

                Intent intent = new Intent(getApplicationContext(), PdfViewerActivity.class);
                startActivity(intent);
                break;

        }
    }
}
