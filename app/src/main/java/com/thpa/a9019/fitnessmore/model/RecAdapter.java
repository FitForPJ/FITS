package com.thpa.a9019.fitnessmore.model;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.cunoraz.gifview.library.GifView;
import com.thpa.a9019.fitnessmore.DBHelper;
import com.thpa.a9019.fitnessmore.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 9019 on 13/11/2560.
 */

public class RecAdapter extends ArrayAdapter<Listcolumn> {

    private ArrayList<Listcolumn> object;
    private DBHelper db;
    private String date;
    private String gifname;


    public RecAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Listcolumn> objects) {
        super(context, resource, objects);
        this.object = objects;
        db = new DBHelper(context);

    }


    public RecAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Listcolumn> objects,String date) {
        super(context, resource, objects);
        this.object = objects;
        db = new DBHelper(context);
        this.date = date;
    }



    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.contentrow, null);
        }

        final TextView textView = (TextView) v.findViewById(R.id.Namefromspin);
        final EditText editText = (EditText) v.findViewById(R.id.editText);
        ImageButton button = (ImageButton) v.findViewById(R.id.btndel);


        textView.setText(object.get(position).getName());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.customdialog);
                dialog.setCancelable(true);
                ImageView imageView = (ImageView) dialog.findViewById(R.id.imgdialog_rec);
                GifView gifView = (GifView) dialog.findViewById(R.id.gifdialog_rec);

                GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
                if(getGifView(textView.getText().toString())) {

                    int id = getContext().getResources().getIdentifier("com.thpa.a9019.fitnessmore:drawable/" + gifname, null, null);
                    Glide.with(getContext()).load(id).into(imageViewTarget);

                }
                else {
                    int id = getContext().getResources().getIdentifier("com.thpa.a9019.fitnessmore:drawable/" + db.getTrainingColumnUrlphoto(textView.getText().toString()), null, null);
                    imageView.setImageResource(id);
                }

                dialog.show();
            }
        });
        editText.setText(object.get(position).getedit());
      editText.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
              object.get(position).setedit(String.format("%s", s));
          }

          @Override
          public void afterTextChanged(Editable s) {


          }
      });

        button.setTag(position);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!(object.get(position).getedit().equals(""))) {
                    if (db.checkFromDate(date, object.get(position).getName())) {
                        db.DeleteDate(date, object.get(position).getName(), Integer.parseInt(object.get(position).getedit()));
                    }
                }
                else {
                    db.DeleteDate2(date, object.get(position).getName());
                }

                object.remove(position);


                notifyDataSetChanged();
            }
        });




        return v;
    }

    @Override
    public void setNotifyOnChange(boolean notifyOnChange) {
        super.setNotifyOnChange(notifyOnChange);
    }

    @NonNull
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Listcolumn getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable Listcolumn item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
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
