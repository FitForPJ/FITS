package com.thpa.a9019.fitnessmore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.cunoraz.gifview.library.GifView;

import java.util.HashMap;

/**
 * Created by 9019 on 18/4/2560.
 */

public class Traincontent extends AppCompatActivity {
    ImageView imageView;
    TextView tvname ;
    TextView tvname1 ;
    TextView tvname2 ;

    private ImageButton imageButton;
    public DBHelper dbHelper;
    private GifView gifView;
    public String gifname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contextmain);
        String sname = getIntent().getStringExtra("train");
        imageButton = (ImageButton) findViewById(R.id.imgback);
        tvname = (TextView)findViewById(R.id.txtvvname);
        tvname1 = (TextView)findViewById(R.id.txtdes);
        tvname2 = (TextView)findViewById(R.id.txtdes1);
        imageView = (ImageView)findViewById(R.id.imgcdc);
        gifView = (GifView) findViewById(R.id.gif1);


        dbHelper = new DBHelper(this);
       dbHelper.getWritableDatabase();


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Traincontent.this, Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });

        tvname1.setText(sname);


        tvname.setText(dbHelper.getTrainingColumnType(sname));


        tvname2.setText(dbHelper.getTrainingColumnDescript(sname));
        tvname2.setMovementMethod(new ScrollingMovementMethod());

        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
        String msg = dbHelper.getTrainingColumnTUrlphoto(sname);
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        if(getGifView(sname)) {

            int id = getResources().getIdentifier("com.thpa.a9019.fitnessmore:drawable/" + gifname, null, null);
            Glide.with(this).load(id).into(imageViewTarget);

        }
        else {
            int id = getResources().getIdentifier("com.thpa.a9019.fitnessmore:drawable/" + dbHelper.getTrainingColumnUrlphoto(sname), null, null);
            imageView.setImageResource(id);
        }

    }

    public boolean getGifView(String name) {
        HashMap<String ,String> img2gif = new HashMap<>();
        img2gif.put("Incline Bench Dumbbell Row","inclinpronedumbbellbentoverrow1");
        if(img2gif.containsKey(name)) {
            gifname = img2gif.get(name);
            return true;
        }
        else
            return false;
    }
}
