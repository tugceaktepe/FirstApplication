package android.tugcekolcu.firstapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

/**
 * Created by tugcekolcu on 3.01.2018.
 */

public class TextPlay extends Activity implements View.OnClickListener{

    EditText etText;
    TextView textOnScreen;
    Button btnSend;
    ToggleButton tbChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textplay);

        init();

        btnSend.setOnClickListener(this);
        tbChange.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        int a = v.getId();

        if (v.getId() == R.id.button) {

            String input = etText.getText().toString();

            if (!"".equals(input) || input != null) {

                textOnScreen.setText(input);

                if (input.equals("Left")) {
                    textOnScreen.setGravity(Gravity.LEFT);
                } else if (input.equals("Right")) {
                    textOnScreen.setGravity(Gravity.RIGHT);
                } else if (input.equals("Center")) {
                    textOnScreen.setGravity(Gravity.CENTER);

                } else if (input.equals("default")) {
                    Random rnd = new Random();
                    textOnScreen.setText("default");
                    textOnScreen.setTextColor(Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));

                    switch (rnd.nextInt(3)) {
                        case 0:
                            textOnScreen.setGravity(Gravity.LEFT);
                            break;
                        case 1:
                            textOnScreen.setGravity(Gravity.RIGHT);
                            break;
                        case 2:
                            textOnScreen.setGravity(Gravity.CENTER);
                            break;
                        default:
                            break;
                    }
                } else {
                    textOnScreen.setText("invalid");
                    textOnScreen.setGravity(Gravity.CENTER);
                }

            }
        } else if(v.getId()==R.id.button2) {
            if (tbChange.isChecked()) { //toggle butonu acik ise
                etText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

            } else { //toggle butonu kapali ise

                etText.setInputType(InputType.TYPE_CLASS_TEXT);
            }

        }

    }
    public void init(){

        etText = (EditText) findViewById(R.id.editText);
        textOnScreen = (TextView) findViewById(R.id.degisecekyazi);
        btnSend = (Button) findViewById(R.id.button);
        tbChange = (ToggleButton) findViewById(R.id.button2);
    }
}
