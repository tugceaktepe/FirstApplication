package android.tugcekolcu.firstapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by tugcekolcu on 23.01.2018.
 */

public class InternalData extends Activity implements View.OnClickListener {

    Button bGetData, bSave;
    TextView textView;
    EditText etInput;
    String FILE_NAME = "InternalString";
    FileOutputStream fos;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreference);
        init();

        bGetData.setOnClickListener(this);
        bSave.setOnClickListener(this);

        ///DOSYAYA KAYDETME

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnGetData:


                new ReadMyFile().execute(FILE_NAME);

                break;
            case R.id.btnSave2: //Kaydedilecek data

                String dataToSave = etInput.getText().toString();
                try {
                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fos.write(dataToSave.getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            default:
                break;

        }

    }

    void init() {

        bGetData = (Button) findViewById(R.id.btnGetData);
        bSave = (Button) findViewById(R.id.btnSave2);
        etInput = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView3);
    }

    public class ReadMyFile extends AsyncTask<String, Integer, String> {


        protected void onPreExecute() { //isleme baslamadan once
        }

        @Override
        protected String doInBackground(String... params) { //islem yapilirken
            String dataFromFile = "";
            try {


                fis = openFileInput(FILE_NAME);

                byte[] bytes = new byte[fis.available()];

                while (fis.read(bytes) != -1) {
                    dataFromFile = new String(bytes);

                }
                fis.close();
                return dataFromFile;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        protected void onProgressUpdate() { //islem yapilirken progress dialog gosterimi icin
        }

        protected void onPostExecute(String result) { //islem sonra erdiginde yapilacaklar icin

            textView.setText(result);
        }
    }


}
