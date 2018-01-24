package android.tugcekolcu.firstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;


/**
 * Created by tugcekolcu on 23.01.2018.
 */

public class Flipper extends Activity implements View.OnClickListener    {

    Button bNext,bBack,bOtomatic;
    ViewFlipper flipper;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewflipper);

        init();
        bNext.setOnClickListener(this);
        bBack.setOnClickListener(this);
        bOtomatic.setOnClickListener(this);

    }
    void init(){

        bOtomatic = (Button) findViewById(R.id.bOtomatik);
        bNext = (Button) findViewById(R.id.bIleri);
        bBack = (Button) findViewById(R.id.bGeri);
        flipper = (ViewFlipper) findViewById(R.id.viewFlipper);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.bGeri:
                if(flipper.isFlipping()){
                    flipper.stopFlipping();
                }
                flipper.showPrevious();
                break;
            case R.id.bIleri:
                if(flipper.isFlipping()){
                    flipper.stopFlipping();
                }
                flipper.showNext();
                break;
            case R.id.bOtomatik:
                flipper.setFlipInterval(2000);
                flipper.startFlipping();
                break;
        }
    }
}
