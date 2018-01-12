package com.thpa.a9019.fitnessmore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.thpa.a9019.fitnessmore.model.DataObject;
import com.thpa.a9019.fitnessmore.model.Listcolumn;

import java.util.ArrayList;


public class HowTrainFragment extends Fragment {
    public DBHelper dbHelper;


    public String name;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<String> Train;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_how_train, container, false);
        Bundle bundle = getArguments();
        String type = (String) bundle.get("type");
        dbHelper = new DBHelper(getActivity());


        Train = dbHelper.getAllTraining(type);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mtrain_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);


//        lvt2.setAdapter(adapter);
//
//        lvt2.setDividerHeight(8);
//
//        lvt2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                name = lvt2.getAdapter().getItem(position).toString();
//                Intent intent = new Intent(getActivity(), Traincontent.class);
//                intent.putExtra("train", name);
//                startActivity(intent);
//
//
//            }
//        });


        return view;

    }

//                Intent intent = new Intent(this, Traincontent.class);
//                intent.putExtra("train", name);
//                startActivity(intent);

    @Override
    public void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                String title1 = ((TextView) mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.textView2)).getText().toString();
                Intent intent = new Intent(getActivity(),Traincontent.class);
                intent.putExtra("train",title1);
                startActivity(intent);


            }
        });
    }

    public ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < Train.size(); index++) {
            dbHelper = new DBHelper(getActivity());
            int id = getResources().getIdentifier("com.thpa.a9019.fitnessmore:drawable/" + dbHelper.getTrainingColumnUrlphoto(Train.get(index).toString()), null, null);
            DataObject obj = new DataObject(id,
                    Train.get(index).toString());
            results.add(index, obj);
        }

        return results;

    }


}



