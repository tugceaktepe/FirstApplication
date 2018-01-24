package android.tugcekolcu.firstapplication;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Created by tugcekolcu on 24.01.2018.
 */

public class ExternalData extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private TextView canWrite, canRead;
    private String state;
    boolean canW, canR;
    Spinner spinner;

    //Dosyamizi kaydedecegimiz dizinler :
    String[] paths = {"Resimler", "Muzikler", "Indirilenler"};


    //file path
    File path = null;

    //kaydedilecek dosya icin

    File filename = null;
    EditText etSaveAs;
    Button btnApprove, btnSaveAs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externaldata);

        init();

        btnApprove.setOnClickListener(this);
        btnSaveAs.setOnClickListener(this);

        checkState();

        //spinner icine string array i  baglamak icin adapter lazim.
        ArrayAdapter myAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, paths);
        spinner.setAdapter(myAdapter);
        spinner.setOnItemSelectedListener(this);


    }

    private void checkState() {

        //State 'e external storage state i atilir.
        state = Environment.getExternalStorageState();


        if (state.equals(Environment.MEDIA_MOUNTED)) { //okunup yazilabilir dizin

            canW = true;
            canR = true;
            canRead.setText("true");
            canWrite.setText("true");


        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) { //Yalnizca read only dizin
            canR = true;
            canW = false;
            canRead.setText("true");
            canWrite.setText("false");

        } else {
            canR = false;
            canW = false;
            canRead.setText("false");
            canWrite.setText("false");

        }


    }

    @SuppressWarnings("ResourceType")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnApprove:
                btnSaveAs.setVisibility(View.VISIBLE);
                btnApprove.setVisibility(View.GONE);
                break;
            case R.id.btnSave:

                //save edilen dosya ismi
                String f = etSaveAs.getText().toString();

                //path ve uzantisi ile tam dosya adi
                filename = new File(path ,f + ".png");

                if (canR && canW) {
                    try {


                        ///Runtime permission
                        ActivityCompat.requestPermissions(ExternalData.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                1);


                        //path yoksa yarat
                        path.mkdirs();

                        //dosyamizi inputStream icinde tut
                        InputStream is = getResources().openRawResource(R.drawable.listview_background);
                        OutputStream os = new FileOutputStream(filename);
                        byte[] data = new byte[is.available()];
                        is.read(data);
                        os.write(data);

                        is.close();
                        os.close();

                        Toast toast = Toast.makeText(ExternalData.this, "Dosya Kaydedildi", Toast.LENGTH_LONG);
                        toast.show();


                        //update files for the user to use

                        MediaScannerConnection.scanFile(ExternalData.this, new String[]{filename.toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Toast toast = Toast.makeText(ExternalData.this, "Tarama tamamlandı", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }


                break;
        }

    }

    void init() {

        btnApprove = (Button) findViewById(R.id.btnApprove);
        btnSaveAs = (Button) findViewById(R.id.btnSave);
        canRead = (TextView) findViewById(R.id.txtCanRead);
        canWrite = (TextView) findViewById(R.id.txtCanWrite);
        spinner = (Spinner) findViewById(R.id.spinner);
        etSaveAs = (EditText) findViewById(R.id.etSavaAs);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        int selectedItem = spinner.getSelectedItemPosition();
        //secilen spinner item a gore adapter icinde atadigimiz pathlere gidicek
        switch (selectedItem) {

            case 0:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                break;
            case 1:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                break;
            case 2:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(ExternalData.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
