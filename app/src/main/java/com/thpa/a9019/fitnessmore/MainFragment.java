package com.thpa.a9019.fitnessmore;

import android.content.Context;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.thpa.a9019.fitnessmore.decorators.EventDecorator;
import com.thpa.a9019.fitnessmore.decorators.HighlightWeekendsDecorator;
import com.thpa.a9019.fitnessmore.decorators.MySelectorDecorator;
import com.thpa.a9019.fitnessmore.decorators.OneDayDecorator;
import com.thpa.a9019.fitnessmore.model.Daily;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;


@RequiresApi(api = Build.VERSION_CODES.N)
public class MainFragment extends Fragment implements OnDateSelectedListener {

    public ViewPager viewPager;

    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();


    MaterialCalendarView widget;
    SendDate SD;
    public DBHelper db;
    public ArrayList<Daily> arrdaily1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        widget = (MaterialCalendarView) view.findViewById(R.id.calendar);
        widget.setOnDateChangedListener(this);
        widget.setShowOtherDates(MaterialCalendarView.SHOW_ALL);
        db = new DBHelper(getActivity());

        TextView yLabel = (TextView) view.findViewById(R.id.yLabel);
        TextView mLabel = (TextView) view.findViewById(R.id.mLabel);
        TextView dLabel = (TextView) view.findViewById(R.id.dLabel);
        TextView eLabel = (TextView) view.findViewById(R.id.eLabel);


        Calendar cal = Calendar.getInstance();
        widget.setSelectedDate(cal.getTime());


        widget.state().edit()

                .setMaximumDate(cal.getTime())
                .commit();

        widget.addDecorators(
                new MySelectorDecorator(getActivity()),
                new HighlightWeekendsDecorator(),
                oneDayDecorator
        );
        new ApiSimulator().executeOnExecutor(Executors.newSingleThreadExecutor());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMMM/d/E", Locale.getDefault());
        String strDate = sdf.format(cal.getTime());
        String[] values = strDate.split("/", 0);

        yLabel.setText(values[0]);
        mLabel.setText(values[1]);
        dLabel.setText(values[2]);
        eLabel.setText(values[3]);
        viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);


//        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@Nullable CalendarView view, int year, int month, int dayOfMonth) {
//                viewPager.setCurrentItem(3);
//
//                Bundle args = new Bundle();
//                args.putInt("day",dayOfMonth);
//                args.putInt("month", month);
//                args.putInt("year", year);
//
//                DatePickerFragment dkpicker = new DatePickerFragment();
//                dkpicker.setArguments(args);
//
//
//                SD.sendData(dayOfMonth,month,year);
//            }
//        });
        return view;
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        viewPager.setCurrentItem(3);

        Bundle args = new Bundle();
        args.putInt("day", date.getDay());
        args.putInt("month", date.getMonth());
        args.putInt("year", date.getYear());

        DatePickerFragment dkpicker = new DatePickerFragment();
        dkpicker.setArguments(args);
        RecFragment recFragment  = new RecFragment();
        recFragment.setArguments(args);

        SD.sendData(date.getDay(), date.getMonth(), date.getYear());
    }

    interface SendDate {
        void sendData(int day, int month, int year);
    }

    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            SD = (SendDate) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {


        @Override
        protected List<CalendarDay> doInBackground(Void... params) {
            ArrayList<CalendarDay> dates = new ArrayList<>();
            arrdaily1 = db.getAlldataPractice1();
            CalendarDay day;
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(0);

            for (int i = 0; i < arrdaily1.size(); i++) {

                String[] date = arrdaily1.get(i).getDate().split("-");
                calendar.set(Integer.parseInt(date[0]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[2]));
                day = CalendarDay.from(calendar);
                dates.add(day);
            }
//            calendar.set(2017, 8, 10, 0, 0, 0);
//            day = CalendarDay.from(calendar);
//            dates.add(day);
//            calendar.set(2017, 8, 11, 0, 0, 0);
//            day = CalendarDay.from(calendar);
//            dates.add(day);
//            calendar.set(2017, 8, 20, 0, 0, 0);
//            day = CalendarDay.from(calendar);
//            dates.add(day);
//            calendar.set(2017, 8, 21, 0, 0, 0);
//            day = CalendarDay.from(calendar);
//            dates.add(day);
            return dates;
        }

        @Override
        protected void onPostExecute(List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);
            if (getActivity().isFinishing()) {
                return;
            }
            widget.addDecorator(new EventDecorator(Color.GREEN, calendarDays));
        }
    }
}
