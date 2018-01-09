package android.tugcekolcu.firstapplication;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by tugcekolcu on 9.01.2018.
 */

public class Ayar extends PreferenceActivity{

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ayar);


    }
}
