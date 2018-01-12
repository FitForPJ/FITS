package com.thpa.a9019.fitnessmore;


import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.thpa.a9019.fitnessmore.model.Daily;
import com.thpa.a9019.fitnessmore.model.DataObject;
import com.thpa.a9019.fitnessmore.model.ListMoreColumn;
import com.thpa.a9019.fitnessmore.model.Listcolumn;
import com.thpa.a9019.fitnessmore.model.RecAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


@RequiresApi(api = Build.VERSION_CODES.N)
public class RecFragment extends Fragment {

    Spinner spinner1;
    public ArrayList<String> listtype;

    ListMoreColumn lradapter;
    public RecAdapter adapter;
    public ArrayList<String> listrec;
    boolean state = false;
    boolean repeatedname = false;

    public Button btnsave;
    public ListView lvt3;

    public DBHelper dbHelper;
    public DBHelper db;

    Button btnAdd;
    Button btnclr;
    ImageButton btnref;

    String tname; //spinner
    ArrayList<DataObject> arrayList;
    ArrayList<Listcolumn> arrayList1;
    ArrayList<Listcolumn> arrayList2;

    public TextView textshowdialog;
    public int count;
    private int lcount;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rec, container, false);

        dbHelper = new DBHelper(getActivity());
        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        btnref = (ImageButton) view.findViewById(R.id.btnref);
        btnsave = (Button) view.findViewById(R.id.btnsaverec);
        btnclr = (Button) view.findViewById(R.id.btnclrrec);
        spinner1 = (Spinner) view.findViewById(R.id.choosefromlv);
        listtype = dbHelper.getAllTrainForSpinner();
        lvt3 = (ListView) view.findViewById(R.id.listview_spin);
        listrec = new ArrayList<>();
        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        textshowdialog = (TextView) view.findViewById(R.id.datesav3);
        Calendar cal = Calendar.getInstance();
        adapter = new RecAdapter(getActivity(), R.layout.contentrow, arrayList2);
        adapter.notifyDataSetChanged();
        lvt3.setAdapter(adapter);

        btnref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList2.clear();
                Checkdaily();
                Toast.makeText(getActivity(), textshowdialog.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        textshowdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment picker = new DatePickerFragment();
                picker.show(getFragmentManager(), "Data Picker");

            }
        });


        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date chosenDate = cal.getTime();
        String formattedDate1 = sdf1.format(chosenDate);
        textshowdialog.setText(formattedDate1);

        textshowdialog.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                arrayList2.clear();
                Checkdaily();
                Toast.makeText(getActivity(), textshowdialog.getText().toString() + "...." + arrayList2.size(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, listtype);
        spinner1.setAdapter(adapterType);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tname = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lvt3.getAdapter().getCount() == 0) {
                    Listcolumn listcolumn = new Listcolumn(tname, "");
                    arrayList2.add(listcolumn);
                } else {
                    if (CheckisDuplicate())
                        Toast.makeText(getActivity(), "You had this item", Toast.LENGTH_SHORT).show();
                    else {
                        Listcolumn listcolumn = new Listcolumn(tname, "");
                        arrayList2.add(listcolumn);
                    }

                }
                adapter = new RecAdapter(getActivity(), R.layout.contentrow, arrayList2, textshowdialog.getText().toString());
                adapter.notifyDataSetChanged();
                lvt3.setAdapter(adapter);


            }
        });
        btnclr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList2.clear();
                adapter.notifyDataSetChanged();
                lvt3.setAdapter(adapter);

            }
        });

//        lradapter.setListener(new AdapterListener());
//        if (sharedPrefs.getString(textshowdialog.getText().toString(), null) != null) {
//            json = sharedPrefs.getString(textshowdialog.getText().toString(), null);
//            Type type = new TypeToken<ArrayList<DataObject>>() {}.getType();
//            arrayList = gson.fromJson(json, type);
//            lradapter = new ListMoreColumn(getActivity(), arrayList,true);
//            lvt3.setAdapter(lradapter);
//        }
//       else {
//            arrayList.clear();
//        }


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (int j = 0; j < lvt3.getChildCount(); j++) {
                    lcount = 0;
                    View v1 = lvt3.getChildAt(j);
                    EditText et = (EditText) v1.findViewById(R.id.editText);
                    if (et.getText().length() == 0) {
                        lcount++;
                    }
                }
                count = lcount;
                if (count > 0) {

                    Toast.makeText(getActivity(), "Please insert value", Toast.LENGTH_SHORT).show();
                } else {

                    Set2Daily();
                }


            }
        });
        return view;
    }

    public void Checkdaily() {
        DBHelper db = new DBHelper(getActivity());
        db.getWritableDatabase();
        ArrayList<Daily> chklist = db.getAlldataPractice();
        for (int i = 0; i < chklist.size(); i++) {
            if (chklist.get(i).getDate().equals(textshowdialog.getText().toString())) {
                Listcolumn listcolumn = new Listcolumn(chklist.get(i).getName(), String.valueOf(chklist.get(i).getTime()));
                arrayList2.add(listcolumn);
            }
        }
        adapter = new RecAdapter(getActivity(), R.layout.contentrow, arrayList2, textshowdialog.getText().toString());
        adapter.notifyDataSetChanged();
        lvt3.setAdapter(adapter);

    }

    private void Set2Daily() {
        int listlength = lvt3.getChildCount();
        View v1;
        EditText et;
        TextView txtview;
        String[] valueofedt = new String[listlength];
        String[] valueoftxt = new String[listlength];

        for (int i = 0; i < listlength; i++) {
            db = new DBHelper(getActivity());
            v1 = lvt3.getChildAt(i);
            et = (EditText) v1.findViewById(R.id.editText);
            txtview = (TextView) v1.findViewById(R.id.Namefromspin);


            if (db.checkFromDate(textshowdialog.getText().toString(), txtview.getText().toString())) {
                Toast.makeText(getActivity(), String.valueOf(db.checkFromDate(textshowdialog.getText().toString(), txtview.getText().toString())) + "#0", Toast.LENGTH_SHORT).show();
                db.DeleteDate2(textshowdialog.getText().toString(), txtview.getText().toString());
                //   db.Updatetime(textshowdialog.getText().toString(), txtview.getText().toString(), Integer.parseInt(et.getText().toString()));
            }

            db.insertDailyPractice(textshowdialog.getText().toString(), txtview.getText().toString(), Integer.parseInt(et.getText().toString()));

            db.close();
        }

        Intent tostat = new Intent(getActivity(), Statperday.class);
        startActivity(tostat);
    }

    protected void displayReceivedDate(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month, day, 0, 0, 0);
        Date chosenDate = cal.getTime();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = sdf1.format(chosenDate);
        textshowdialog.setText(formattedDate);
    }

    public boolean CheckisDuplicate() {
        boolean isdup = false;
        for (int i = 0; i < lvt3.getAdapter().getCount(); i++) {
            if (arrayList2.get(i).getName().equals(tname)) {
                isdup = true;
                break;
            } else
                isdup = false;

        }
        return isdup;
    }

    private class AdapterListener implements ListMoreColumn.AdapterListener {
        @Override
        public void onSend(ArrayList<Listcolumn> lst) {
            lradapter = new ListMoreColumn(getActivity(), lst, true);
            lvt3.setAdapter(lradapter);
        }
    }


}
