package android.tugcekolcu.firstapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tugcekolcu on 8.01.2018.
 */

public class ResimCek extends Activity implements View.OnClickListener {

    ImageButton ibtakePic;
    ImageView image;
    Button btnMakeWallpaper;
    Intent intent;
    final static  int requestCode = 0;
    Bitmap bmp;

    @SuppressWarnings("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kamera);
        init();
        InputStream is = getResources().openRawResource(R.mipmap.ic_launcher);
        bmp = BitmapFactory.decodeStream(is);
        ibtakePic.setOnClickListener(this);
        btnMakeWallpaper.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnTakePic:

                intent= new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,requestCode);

                break;
            case R.id.btnMakeWallpaper:

                try {
                    getApplicationContext().setWallpaper(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            default:
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) { //resim duzgun cekildiyse

            Bundle bohca = data.getExtras(); //Bundle veri yigini
            bmp  = (Bitmap) bohca.get("data"); //icinden veriyi al
            image.setImageBitmap(bmp); //bitmap olarak imageView a ekle
        }

    }

    void init(){

        ibtakePic = (ImageButton) findViewById(R.id.btnTakePic);
        btnMakeWallpaper=(Button) findViewById(R.id.btnMakeWallpaper);
        image = (ImageView) findViewById(R.id.cekilenResim);
    }
}
