package com.thpa.a9019.fitnessmore.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thpa.a9019.fitnessmore.R;

import java.util.ArrayList;

/**
 * Created by 9019 on 13/9/2560.
 */

public class Adaplistdaily extends BaseAdapter {

    ArrayList<Daily > dlist = new ArrayList<>() ;
    Context mcontext;


    public Adaplistdaily(Context context, ArrayList<Daily> list) {
        this.mcontext = context;
        this.dlist = list;

    }

    @Override
    public int getCount() {
        return dlist.size();
    }

    @Override
    public Object getItem(int position) {
        return dlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mcontext,R.layout.contentrow1,null);
        TextView txtdate = (TextView) view.findViewById(R.id.datedaily);
        TextView textMove = (TextView) view.findViewById(R.id.movedaily);
        TextView texttimes = (TextView) view.findViewById(R.id.timesdaily);

        txtdate.setText(dlist.get(position).getDate());
        textMove.setText(dlist.get(position).getName());

        texttimes.setText(String.valueOf(dlist.get(position).getTime()));
        return  view;
    }
}
