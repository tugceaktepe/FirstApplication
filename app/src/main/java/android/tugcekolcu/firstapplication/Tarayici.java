package android.tugcekolcu.firstapplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by tugcekolcu on 10.01.2018.
 */

public class Tarayici  extends Activity implements View.OnClickListener{


    Button btGo, btBack, btForward, btRefresh, btDeleteHis;
    EditText etAddress;
    WebView browser;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);

        browser = (WebView) findViewById(R.id.webView);

        browser.getSettings().setJavaScriptEnabled(true); //java sciprt enable hale getiriyor.
        browser.getSettings().setLoadWithOverviewMode(true); //sayfa tasmasini onlemek icin
        browser.getSettings().setUseWideViewPort(true);

        browser.setWebViewClient(new MyClient());
        browser.loadUrl("www.google.com");

        btBack = (Button) findViewById(R.id.btnBack);
        btGo = (Button) findViewById(R.id.btnGo);
        btForward = (Button) findViewById(R.id.btnForward);
        btRefresh = (Button) findViewById(R.id.btnRefresh);
        btDeleteHis = (Button) findViewById(R.id.btnDelete);

        etAddress = (EditText)findViewById(R.id.etAddress);

        btBack.setOnClickListener(this);
        btGo.setOnClickListener(this);
        btRefresh.setOnClickListener(this);
        btDeleteHis.setOnClickListener(this);
        btForward.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnGo:
                String address = etAddress.getText().toString();
                browser.loadUrl(address);

                //input girildikten sonra klavyeyi gizleme

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etAddress.getWindowToken(),0);

                break;
            case R.id.btnBack:
                    if(browser.canGoBack()){
                        browser.goBack();
                    }

                break;
            case R.id.btnForward:
                if(browser.canGoForward()){
                    browser.goForward();
                }

                break;
            case R.id.btnRefresh:

                browser.reload();

                break;
            case R.id.btnDelete:

                browser.clearHistory();

                break;
        }

    }
}
