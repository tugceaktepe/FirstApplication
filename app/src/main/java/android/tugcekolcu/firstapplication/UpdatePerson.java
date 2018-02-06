package android.tugcekolcu.firstapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by tugcekolcu on 6.02.2018.
 */

public class UpdatePerson extends Activity implements View.OnClickListener {

    EditText etName, etAge, etSurname;
    TextView tvID;
    Button btnUpdate, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateperson);

        init();
        btnBack.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        String a = getIntent().getExtras().getString("Id");
        tvID.setText(getIntent().getExtras().getString("Id"));
        etName.setText(getIntent().getExtras().getString("Name"));
        etSurname.setText(getIntent().getExtras().getString("Surname"));
        etAge.setText(getIntent().getExtras().getString("Age"));


    }

    void init() {
        etAge = (EditText) findViewById(R.id.etAge);
        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurname);
        tvID = (TextView) findViewById(R.id.textViewID);
        btnUpdate = (Button) findViewById(R.id.btnUpdatePerson);
        btnBack = (Button) findViewById(R.id.btnBackFromUpdate);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnUpdatePerson) {

            if (!etName.getText().toString().isEmpty() && !etSurname.getText().toString().isEmpty() && !etAge.getText().toString().isEmpty()) {

                int id =  Integer.parseInt(tvID.getText().toString());
                String name = etName.getText().toString();
                String surname = etSurname.getText().toString();
                String age = etAge.getText().toString();
                Person personToUpdate = new Person (id,name,surname,age);
                PersonDatabase db = new PersonDatabase(UpdatePerson.this);
                try {
                    db.updatePerson(personToUpdate);
                } catch (Exception e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Update Result: ");
                    builder.setMessage(e.getMessage());
                    builder.show();
                } finally {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Update Result: ");
                    builder.setMessage("Update Successful");
                    builder.show();
                }
            }

        } else if (v.getId() == R.id.btnBackFromUpdate) {
            onBackPressed();
        }
    }

}
