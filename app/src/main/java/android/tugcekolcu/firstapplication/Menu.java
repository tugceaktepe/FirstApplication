package android.tugcekolcu.firstapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
/**
 * Created by tugcekolcu on 3.01.2018.
 */

public class Menu extends ListActivity {

    String myClasses [] = {"MainActivity","ListeOrnek","TextPlay","Email","ResimCek","Tablar","Tarayici", "Flipper"};

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Full ekran calisma
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //listeyi stringe baglamak icin adapter lazim.
        ArrayAdapter myAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,myClasses);
        setListAdapter(myAdapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String selectedItem = myClasses[position];
        try {
            Class selectedClass = Class.forName("android.tugcekolcu.firstapplication." + selectedItem);
            Intent intent = new Intent(this,selectedClass);
            startActivity(intent);
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }



    @Override
    protected void onPause() {
        super.onPause();
        Log.e("LIFECYCLE", "Menu onPause çalıştırıldı");
        //player.release();
        finish();
    }

}
