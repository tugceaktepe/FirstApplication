package android.tugcekolcu.firstapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by tugcekolcu on 23.01.2018.
 */

public class SharedPreferenceScreen extends Activity implements View.OnClickListener {

    Button bGetData, bSave;
    TextView textView;
    EditText etInput;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreference);
        init();

        bGetData.setOnClickListener(this);
        bSave.setOnClickListener(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);//projeye sharedPreference eklendi.

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnGetData:


                String readString ="";
                readString = sharedPreferences.getString("veri","VeriBulunamadi").toString();
                textView.setText(readString);

               break;
            case R.id.btnSave2:


                Editor editor=sharedPreferences.edit();

                String data = etInput.getText().toString();

                editor.putString("veri",data);
                editor.apply();


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
}
