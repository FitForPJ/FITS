package com.thpa.a9019.fitnessmore;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 9019 on 19/8/2560.
 */


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    DBHelper db;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

//        if (getArguments().getString("state").equals(String.valueOf(1))) {
//            String date = getArguments().getString("day") + "/" + getArguments().getString("month2") + "/" + getArguments().getString("year");
//            textshowdialog.setText(date);
//
//        } else {
//            String date = dayOfMonth + "/" + (month + 1) + "/" + year;
//            textshowdialog.setText(date);
//        }

        TextView textshowdialog = (TextView) getActivity().findViewById(R.id.datesav3);

        // Create a Date variable/object with user chosen date
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month, dayOfMonth, 0, 0, 0);
        Date chosenDate = cal.getTime();

        // Format the date using style and locale
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String formattedDate = sdf.format(chosenDate);

        // Display the chosen date to app interface

        textshowdialog.setText(formattedDate);

    }
}
