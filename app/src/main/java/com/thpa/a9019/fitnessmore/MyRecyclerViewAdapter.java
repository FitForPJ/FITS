package com.thpa.a9019.fitnessmore;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thpa.a9019.fitnessmore.model.DataObject;
import com.thpa.a9019.fitnessmore.model.Listcolumn;

import java.util.ArrayList;

/**
 * Created by 9019 on 22/10/2560.
 */

class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {

    public ArrayList<DataObject> mDataset;
    private static MyClickListener myClickListener;

    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataSet) {
        mDataset = myDataSet;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        holder.label.setImageResource(mDataset.get(position).getmText1());
        holder.mName.setText(mDataset.get(position).getmText2());
        holder.mName.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        ImageView label;
        TextView mName;

        public DataObjectHolder(View itemView) {
            super(itemView);
            label = (ImageView) itemView.findViewById(R.id.imgView);
            mName = (TextView) itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }


    }
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }


}
