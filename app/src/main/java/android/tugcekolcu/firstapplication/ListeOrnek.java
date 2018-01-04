package android.tugcekolcu.firstapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ArrayAdapter;

/**
 * Created by tugcekolcu on 3.01.2018.
 */

public class ListeOrnek extends Activity{

    String [] countries = {"Turkey", "Germany", "France", "China","Japan"};
    ListView countryList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listeornek);

        countryList = findViewById(R.id.listeornek);

        //veri kaynagi ile veriye ihtiyaci olan yapiyi baglamaya yarar.
        //veri kaynagindaki verileri gorsel ogelerle kullanabilmeye imkan verir.
        ArrayAdapter myAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1, countries);
        countryList.setAdapter(myAdapter);

        OnItemClickListener listener = new OnItemClickListener() {
            @Override // position : tiklanan item in yerini veriyor.
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(ListeOrnek.this);
                alert.setMessage(countries[position]);
                alert.setCancelable(false);
                alert.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.create().show();
            }
        };
        countryList.setOnItemClickListener(listener);


    }
}
