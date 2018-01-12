package com.thpa.a9019.fitnessmore;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thpa.a9019.fitnessmore.model.TypeTrain;

import java.util.ArrayList;

/**
 * Created by COM on 14/4/2560.
 */

class ListType extends BaseAdapter {
    public Context context;
    ArrayList<TypeTrain> strName;



    public ListType(Context mContext, ArrayList<TypeTrain> strName ){
        this.context = mContext;
        this.strName = strName;
    }
    @Override
    public int getCount() {
        return strName.size();
    }

    @Override
    public Object getItem(int position) {
        return strName.get(position).getName();
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context,R.layout.item_listview1,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imglisttype);
        int did = context.getResources().getIdentifier("com.thpa.a9019.fitnessmore:drawable/" + strName.get(position).getImg(), null, null);
        imageView.setImageResource(did);
        TextView textView = (TextView) view.findViewById(R.id.tv_product_name1);
        textView.setText(strName.get(position).getName());
        textView.setTag(position);
        return view;
    }


}
