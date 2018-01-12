package com.thpa.a9019.fitnessmore;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thpa.a9019.fitnessmore.model.TypeTrain;

import java.util.ArrayList;
import java.util.List;


public class TraintypeFragment extends Fragment {

    private List<String> TrainType = null;

    private DBHelper dbHelper;
    private ListView lvt;
    private  ListType adapter  ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_traintype, container, false);
        lvt = (ListView) view.findViewById(R.id.listview_type);

        ArrayList<TypeTrain> list  = new ArrayList<TypeTrain>();
        TypeTrain type1 = new TypeTrain("Abdominal","nabdominal");
        list.add(type1);
        TypeTrain type2= new TypeTrain("Bicep","nbicep");
        list.add(type2);
        TypeTrain type3= new TypeTrain("Calve","ncalve");
        list.add(type3);
        TypeTrain type4= new TypeTrain("Back","nback");
        list.add(type4);
        TypeTrain type5= new TypeTrain("Chest","nchest");
        list.add(type5);
        TypeTrain type6= new TypeTrain("Glutes","nglutes");
        list.add(type6);
        TypeTrain type7= new TypeTrain("Hamstring","nhamstring");
        list.add(type7);
        TypeTrain type8= new TypeTrain("Quadricep","nquadricep");
        list.add(type8);
        TypeTrain type9= new TypeTrain("Shoulder","nshoulder");
        list.add(type9);
        TypeTrain type10= new TypeTrain("Trapezius","ntrapezius");
        list.add(type10);
        TypeTrain type11= new TypeTrain("Tricep","ntricep");
        list.add(type11);


            adapter = new ListType(getActivity(), list);
            //Set adapter for listviewv
                lvt.setAdapter(adapter);

        lvt.setDividerHeight(12);

       lvt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) parent.getItemAtPosition(position);
                        Bundle bundle = new Bundle();
                        bundle.putString("type",name);
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        HowTrainFragment howTrainFragment = new HowTrainFragment();
                        howTrainFragment.setArguments(bundle);
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.fragmentcontainer, howTrainFragment);

                        transaction.commit();



            }
        });
        return view;
    }


}
