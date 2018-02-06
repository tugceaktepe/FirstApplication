package android.tugcekolcu.firstapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by tugcekolcu on 28.01.2018.
 */

public class InsanlarDBUygulamasi extends Activity implements View.OnClickListener {

    EditText etName, etAge, etSurname, etID;
    Button btnSaveInfo, btnGetInfo, btnSearch, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kisiler);
        init();
        btnGetInfo.setOnClickListener(this);
        btnSaveInfo.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnSaveInfo: //Add new person

                String name = etName.getText().toString();
                String surname = etSurname.getText().toString();
                String age = etAge.getText().toString();

                try {
                    Log.d("Insert: ", "Inserting ..");
                    PersonDatabase db = new PersonDatabase(InsanlarDBUygulamasi.this);
                    Person p = new Person(name, surname, age);
                    db.addPerson(p);
                } catch (Exception e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("ERROR");
                    builder.setMessage(e.getMessage());
                    builder.show();
                } finally {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("AndroidTutorial");
                    builder.setMessage("Insert Successful");
                    builder.show();
                }

                break;
            case R.id.btnGetInfo: //get All person list

                Class selectedClass = null;
                try {
                    selectedClass = Class.forName("android.tugcekolcu.firstapplication.PersonList");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(this, selectedClass);
                startActivity(intent);
                break;
            case R.id.btnSearch: //search person by id

                if(!etID.getText().toString().isEmpty()) {
                    int id = Integer.parseInt(etID.getText().toString());
                    Person p = null;
                    try {
                        Log.d("Search: ", "Searching ..");
                        PersonDatabase db = new PersonDatabase(InsanlarDBUygulamasi.this);
                        p = db.getPerson(id);

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if(p==null){
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setTitle("Search Result ");
                            builder.setMessage("Record not found");
                            builder.show();
                            return;
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Search Result ");
                        builder.setMessage("ID:" + p.getId() + "\nName-Surname:" + p.getName() + "  " + p.getSurname() + "\nAge:" + p.getAge());
                        builder.show();
                    }
                }

                break;
            case R.id.btnDelete: //delete a record from db

                String strId = etID.getText().toString();
                if (strId.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Warning: ");
                    builder.setMessage("Please enter ID to delete a record");
                    builder.show();
                    return;
                } else {
                    int pId = Integer.parseInt(strId);
                    Log.d("Delete: ", "Deleting ..");
                    PersonDatabase db = new PersonDatabase(InsanlarDBUygulamasi.this);

                    try {
                        db.deletePerson(pId);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Delete Result: ");
                        builder.setMessage("Delete Successful");
                        builder.show();
                    }

                }

                break;
            case R.id.btnUpdate: //update a record

                String idUpdate = etID.getText().toString();
                if (idUpdate.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Warning: ");
                    builder.setMessage("Please enter ID to update a record");
                    builder.show();
                    return;
                } else {
                    int pId = Integer.parseInt(idUpdate);
                    Person person = null;
                    try {
                        Log.d("Search: ", "Searching ..");
                        PersonDatabase db = new PersonDatabase(InsanlarDBUygulamasi.this);
                        person = db.getPerson(pId);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(person==null){
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Search Result");
                        builder.setMessage("Record not found");
                        builder.show();
                        return;
                    }
                    Class aClass = null;
                    try {
                        aClass = Class.forName("android.tugcekolcu.firstapplication.UpdatePerson");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    Intent i = new Intent(this, aClass);
                    i.putExtra("Id", String.valueOf(person.getId()));
                    i.putExtra("Name", person.getName());
                    i.putExtra("Surname", person.getSurname());
                    i.putExtra("Age", person.getAge());
                    startActivity(i);


                }

                break;
        }

    }

    void init() {
        etAge = (EditText) findViewById(R.id.etAge);
        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurname);
        etID = (EditText) findViewById(R.id.editTextID);
        btnGetInfo = (Button) findViewById(R.id.btnGetInfo);
        btnSaveInfo = (Button) findViewById(R.id.btnSaveInfo);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
    }
}
