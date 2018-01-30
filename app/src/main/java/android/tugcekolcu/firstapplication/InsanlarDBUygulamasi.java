package android.tugcekolcu.firstapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by tugcekolcu on 28.01.2018.
 */

public class InsanlarDBUygulamasi extends Activity implements View.OnClickListener {

    EditText etName, etAge, etSurname;
    Button btnSaveInfo, btnGetInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kisiler);
        init();
        btnGetInfo.setOnClickListener(this);
        btnSaveInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnSaveInfo:

                String name = etName.getText().toString();
                String surname = etSurname.getText().toString();
                String age = etAge.getText().toString();

                try {
                    Log.d("Insert: ", "Inserting ..");
                    PersonDatabase db = new PersonDatabase(InsanlarDBUygulamasi.this);
                    Person p = new Person(name,surname,age);
                    db.addPerson(p);
                }
                catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("ERROR");
                    builder.setMessage(e.getMessage());
                    builder.show();
                }
                finally {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("AndroidTutorial");
                    builder.setMessage("Insert Operation SUCCESSFUL!");
                    builder.show();
                }

                break;
            case R.id.btnGetInfo:

                Class selectedClass = null;
                try {
                    selectedClass = Class.forName("android.tugcekolcu.firstapplication.PersonList");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(this,selectedClass);
                startActivity(intent);
                break;
        }

    }

    void init() {
        etAge = (EditText) findViewById(R.id.etAge);
        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurname);
        btnGetInfo = (Button) findViewById(R.id.btnGetInfo);
        btnSaveInfo = (Button) findViewById(R.id.btnSaveInfo);

    }
}
