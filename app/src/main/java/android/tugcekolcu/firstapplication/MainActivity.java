package android.tugcekolcu.firstapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnAdd,btnSub;
    TextView tVResult;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //calistiginda Layout dosyasindaki activity main xml ini ekrana yukluyor.


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //tasarimsal islemler layout icinde yapilir.Layout atamasi yapilir

        result=0;
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        tVResult = (TextView) findViewById(R.id.tVResult);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result++;
                tVResult.setText("Sonuç = " + result);
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result--;
                tVResult.setText("Sonuç = " + result);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        super.onCreateOptionsMenu(menu);
        MenuInflater myMenu = getMenuInflater();
        myMenu.inflate(R.menu.ilk_menum,menu);

        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("LIFECYCLE", "MainActivity onPause çalıştırıldı");

    }



}
