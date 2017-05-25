package com.example.ziolson.fadeanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void fade(View view) {
        ImageView empire = (ImageView)findViewById(R.id.empire);
        ImageView empire1 = (ImageView)findViewById(R.id.empire1);

        empire.animate().scaleX(0.5f).scaleY(0.5f).setDuration(1000);

        /*if (empire.getAlpha() > 0) {
            empire.animate().alpha(0f).setDuration(2000);
            empire1.animate().alpha(1f).setDuration(2000);
        }
        else {
            empire1.animate().alpha(0f).setDuration(2000);
            empire.animate().alpha(1f).setDuration(2000);
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
