package android.tugcekolcu.firstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

/**
 * Created by tugcekolcu on 10.01.2018.
 */

public class Tablar extends Activity {


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablar);

        TabHost tabhost = (TabHost) findViewById(R.id.tabHost); //ismine dikkat et.
        tabhost.setup();

        TabSpec features ;
        features = tabhost.newTabSpec("tag1"); //tab a etiket ver
        features.setContent(R.id.tab1); //tabi set et
        features.setIndicator("1st tab"); //tabta gorunecek isim
        tabhost.addTab(features); //tabhost a ekle

        features = tabhost.newTabSpec("tag2");
        features.setContent(R.id.tab1);
        features.setIndicator("2nd tab");
        tabhost.addTab(features);

        features = tabhost.newTabSpec("tag2");
        features.setContent(R.id.tab1);
        features.setIndicator("3rd tab");
        tabhost.addTab(features);



    }
}
