package com.thpa.a9019.fitnessmore;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 9019 on 17/4/2560.
 */

public class ListTrain extends BaseAdapter {
    private Context context;
    private List<String> Train;

    public ListTrain(Context mContext, List<String> Train ){
        this.context = mContext;
        this.Train = Train;
    }
    @Override
    public int getCount() {
        return  Train.size();
    }

    @Override
    public Object getItem(int position) {
        return Train.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context,R.layout.item_listview,null);
        TextView textView = (TextView) view.findViewById(R.id.tv_product_name);
        textView.setText(Train.get(position).toString());

        return view;
    }
}
