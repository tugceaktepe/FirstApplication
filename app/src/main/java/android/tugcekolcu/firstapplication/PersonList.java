package android.tugcekolcu.firstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tugcekolcu on 28.01.2018.
 */

public class PersonList extends Activity implements View.OnClickListener{

    Button btnBack;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kisilistesi);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        TextView textView= findViewById(R.id.tvTumKayitlar);

        PersonDatabase db = new PersonDatabase(this);

        String allRecords="";

        Log.d("Reading: ", "Reading all contacts..");
        List<Person> pList = db.getAllList();
        for(int i=0; i<pList.size(); i++){
            allRecords += pList.get(i).getId() + "  " + pList.get(i).getName() + "  " + pList.get(i).getSurname() + "  " + pList.get(i).getAge()+ "\n";
        }

        textView.setText(allRecords);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnBack){
            onBackPressed();
        }


    }
}
