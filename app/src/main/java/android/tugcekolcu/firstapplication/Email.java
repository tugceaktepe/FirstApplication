package android.tugcekolcu.firstapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by tugcekolcu on 8.01.2018.
 */

public class Email extends Activity implements View.OnClickListener {

    Button btnSend;
    EditText etReciever, etMessage, etTitle, etEndLine;
    String mailAddresses, message, title, endLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emailgonder);
        init();
        btnSend.setOnClickListener(this);
    }

    void init(){

        btnSend = (Button) findViewById(R.id.btnGonder);
        etReciever = (EditText) findViewById(R.id.etNames);
        etMessage = (EditText) findViewById(R.id.etMessage);
        etEndLine = (EditText) findViewById(R.id.etEndLine);
        etTitle = (EditText) findViewById(R.id.etTitle);

    }
    void getAllText(){

        if(!"".equals(etEndLine.getText().toString()) && !"".equals(etTitle.getText().toString()) && !"".equals(etReciever.getText().toString()) && !"".equals(etMessage.getText().toString())){

            mailAddresses=etReciever.getText().toString();
            endLine=etEndLine.getText().toString();
            title=etTitle.getText().toString();
            message=etMessage.getText().toString();

        }

    }

    @Override
    public void onClick(View v) {

            getAllText();

            String mails[] = {mailAddresses};
            String content = "Merhabalar " + mailAddresses + "\nSöylemek istediğim bazı şeyler var. Şöyle ki : \n" + message +"Umarım düşüncelerime değer veriliyor ve mailimi bitiriyorum.\n"
                    +endLine + "\nMailin sonu...";

            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, mails);
            emailIntent.putExtra(android.content.Intent.EXTRA_TITLE,title);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,content);
            startActivity(emailIntent);

    }
}
