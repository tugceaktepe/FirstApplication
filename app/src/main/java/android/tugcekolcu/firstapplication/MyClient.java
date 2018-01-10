package android.tugcekolcu.firstapplication;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by tugcekolcu on 10.01.2018.
 */

class MyClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {


        view.loadUrl(url);

        return true;
    }
}
