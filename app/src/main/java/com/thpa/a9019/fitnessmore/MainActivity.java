package com.thpa.a9019.fitnessmore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.cunoraz.gifview.library.GifView;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private Runnable runnable;
    private String gifname = "kneetokick2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   ;
        ImageView imageView = (ImageView) findViewById(R.id.gif2);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
        int id = getResources().getIdentifier("com.thpa.a9019.fitnessmore:drawable/" + gifname, null, null);
        Glide.with(this).load(id).into(imageViewTarget);
        handler = new Handler();

        runnable = new Runnable() {
            public void run() {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
            }
        };
    }
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 2500);
    }

    public void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }
}

