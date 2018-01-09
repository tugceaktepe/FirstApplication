package android.tugcekolcu.firstapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by tugcekolcu on 2.01.2018.
 */

public class HosgeldinEkrani extends Activity {

    MediaPlayer player ;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hosgeldin);

        //arka planda muzik calmasini saglar
        player = MediaPlayer.create(HosgeldinEkrani.this,R.raw.raw);


        //preferences tan deger cekip muzigin calinip calinmayacagini belirleyecez

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean calmaDurumu = sharedPreferences.getBoolean("cbTurnMusic",true);

        if(calmaDurumu){
            player.start();
        }

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
        //muzigi durdurur
        player.release();
        finish();
    }




}
