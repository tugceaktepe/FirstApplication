package android.tugcekolcu.firstapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by tugcekolcu on 2.01.2018.
 */

public class HosgeldinEkrani extends Activity {

    //MediaPlayer player ;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hosgeldin);
        //player = MediaPlayer.create(HosgeldinEkrani.this,R.raw.laislabonita);
        //player.start();
        Thread zamanlayici = new Thread(){

            public void run(){
                    try {
                        sleep(4000);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    finally {
                        Intent mainActivity = new Intent("com.android.tugcekolcu.firstapplication.MENU");
                        startActivity(mainActivity);
                    }
            }
        };

        zamanlayici.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("LIFECYCLE", "Hosgeldin onPause çalıştırıldı");
        //player.release();
        finish();
    }




}
