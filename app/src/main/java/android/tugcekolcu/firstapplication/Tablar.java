package android.tugcekolcu.firstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Created by tugcekolcu on 10.01.2018.
 */

public class Tablar extends Activity implements OnClickListener {

    TabHost tabhost;
    Button newTab, bstart,bstop;
    TextView result;

    long start= (long)0;
    long stop= (long) 0;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablar);


        tabhost = (TabHost) findViewById(R.id.tabHost); //ismine dikkat et.

        bstart = (Button) findViewById(R.id.bstart);
        bstop = (Button) findViewById(R.id.bstop);
        result = (TextView) findViewById(R.id.etResult) ;

        bstart.setOnClickListener(this);
        bstop.setOnClickListener(this);

        /*newTab = (Button) findViewById(R.id.bYeniTab);
        newTab.setOnClickListener(this);*/

        tabhost.setup();

        TabSpec features ;
        features = tabhost.newTabSpec("tag1"); //tab a etiket ver
        features.setContent(R.id.tab1); //tabi set et
        features.setIndicator("Chronometer"); //tabta gorunecek isim
        tabhost.addTab(features); //tabhost a ekle

        features = tabhost.newTabSpec("tag2");
        features.setContent(R.id.tab2);
        features.setIndicator("2nd tab");
        tabhost.addTab(features);

        features = tabhost.newTabSpec("tag2");
        features.setContent(R.id.tab3);
        features.setIndicator("3rd tab");
        tabhost.addTab(features);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            /*case R.id.bYeniTab:

                TabSpec newTab = tabhost.newTabSpec("tag1"); //tab a etiket ver
                newTab.setContent(new TabHost.TabContentFactory() {
                    @Override
                    public View createTabContent(String tag) {
                        TextView text = new TextView(Tablar.this);
                        text.setText("New tab created");
                        return text;
                    }
                });
                newTab.setIndicator("New");
                tabhost.addTab(newTab);
                break;*/
            case R.id.bstart:

                start = System.currentTimeMillis(); //o anki zamani alir.
                break;
            case R.id.bstop:

                stop = System.currentTimeMillis();

                if(start!=0){
                    long sonuc = stop - start;
                    int miliSn = (int) sonuc;
                    int sn = (int) sonuc/1000;
                    int dk = (int) sn/60;

                    result.setText(String.format("%d : %02d : %02d", dk,sn,miliSn));
                }

                break;
        }
    }
}
