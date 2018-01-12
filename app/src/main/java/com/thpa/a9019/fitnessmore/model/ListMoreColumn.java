package com.thpa.a9019.fitnessmore.model;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.thpa.a9019.fitnessmore.R;
import com.thpa.a9019.fitnessmore.RecFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by 9019 on 20/4/2560.
 */

public class ListMoreColumn extends BaseAdapter {

    ArrayList<String> list = new ArrayList<>();
    Context mcontext;
    ArrayList<Listcolumn> list1 = new ArrayList<>();
    ArrayList<Listcolumn> listcol = new ArrayList<>();
    private boolean difcontruct = false;

    String name;
    Listcolumn itemstr;
    private AdapterListener mListener;



   public ListMoreColumn(Context context,ArrayList<Listcolumn> lst,boolean difcontruct){
       this.mcontext = context;
       listcol = lst;
       this.difcontruct = difcontruct;

   }

    public ListMoreColumn(Context context, ArrayList<String> list) {
        this.mcontext = context;
        this.list = list;

    }



    @Override
    public int getCount() {
        return list1.size();
    }

    @Override
    public Object getItem(int position) {
        return list1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        final SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(mcontext.getApplicationContext());
        final SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        final Gson gson = new Gson();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.contentrow, null);
        }
        final TextView textView = (TextView) view.findViewById(R.id.Namefromspin);
        EditText editText = (EditText) view.findViewById(R.id.editText);
        ImageButton button = (ImageButton) view.findViewById(R.id.btndel);




        if(difcontruct){
            textView.setText(listcol.get(position).getName());
        }
        else{
            textView.setText(list.get(position));
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(), " " + position, Toast.LENGTH_SHORT).show();
            }
        });

        if(difcontruct){
            editText.setText(listcol.get(position).getedit());
        }
        button.setTag(position);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name = String.format("%s", s);
                itemstr = new Listcolumn((String) textView.getText(), name);
                Toast.makeText(mcontext,itemstr.getedit(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        list1.add(position, itemstr);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list1.remove(position);
                list.remove(position);
                prefsEditor.putString("itemlist", gson.toJson(list1));
                prefsEditor.commit();
                //  list1.remove(position);//or some other task

                notifyDataSetChanged();
            }
        });



        return view;
    }
    public interface AdapterListener {
        void onSend(ArrayList<Listcolumn> lst);
    }
    public void setListener(AdapterListener listener) {
        this.mListener = listener;
    }



}


