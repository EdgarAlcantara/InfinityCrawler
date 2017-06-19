package com.example.edgar.infinitycrawler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import static android.R.attr.button;
import static com.example.edgar.infinitycrawler.R.string.salir;

/**
 * <p>Activity principal</p>
 * @author Edgar Alcántara
 * @version 1.0
 */
public class MainActivity extends Activity {
    Surface pantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        pantalla = new Surface(this);
        pantalla.setKeepScreenOn(true);
        setContentView(pantalla);

    }

    @Override
    protected void onPause() {
        super.onPause();
        pantalla.pausar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        pantalla.resumir();


    }
}