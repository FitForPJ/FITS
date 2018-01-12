package com.thpa.a9019.fitnessmore;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;


import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;
import com.thpa.a9019.fitnessmore.model.Adaplistdaily;
import com.thpa.a9019.fitnessmore.model.Daily;

import java.lang.ref.SoftReference;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class Statperday extends AppCompatActivity implements OnChartGestureListener,
        OnChartValueSelectedListener {
    DBHelper db;

    private static String TAG = "Statperday";
    public GraphView graph;
    private ListView lvdp;
    public ArrayList<Daily> arrdaily;
    public ArrayList<Daily> arrdaily1;

    public LineGraphSeries<DataPoint> series;
    float counter =  0;
    public Adaplistdaily adtdaily;

    private BarChart Chart;
    public PointsGraphSeries<DataPoint> series1;
    private HashMap <Float,String> numMap;
    private String [] xValues ;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statperday);
        Log.d(TAG, "onCreate: starting to create chart");
        db = new DBHelper(this);
        db.getReadableDatabase();
        arrdaily = new ArrayList<>();

        arrdaily1 = new ArrayList<>();

        arrdaily = db.getAlldataPractice();
        arrdaily1 = db.getAlldataPractice1();
        lvdp = (ListView) findViewById(R.id.listpractice);
        Chart = (BarChart) findViewById(R.id.chart);
        Chart.setOnChartGestureListener(this);
        Chart.setOnChartValueSelectedListener(this);

        setData();

        // get the legend (only possible after setting data)
        Legend l = Chart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.SQUARE);













//        DataPoint[] dp = new DataPoint[arrdaily1.size()];

//        for (int i = 0; i < arrdaily1.size(); i++) {
//
//            Calendar calendar = Calendar.getInstance();
//            String[] date = arrdaily1.get(i).getDate().split("/");
//            calendar.set(Integer.parseInt(date[2]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]));
//            Date mdate = calendar.getTime();
//            System.out.println(mdate);
//            dp[i] = new DataPoint(mdate, arrdaily1.get(i).getTime());
//
//        }
//        series = new LineGraphSeries<>(dp);
//        series1 = new PointsGraphSeries<>(dp);
//        series1.setOnDataPointTapListener(new OnDataPointTapListener() {
//            @Override
//            public void onTap(Series series, DataPointInterface dataPoint) {
//                Date d = new java.sql.Date((long) dataPoint.getX());
//                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
//                String formatted = format1.format(d.getTime());
//                Toast.makeText(Statperday.this, formatted + " " + dataPoint.getY(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        graph.addSeries(series);
//        graph.addSeries(series1);
//        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
//        graph.getGridLabelRenderer().setNumHorizontalLabels(4); // only 4 because of the space
//
//
//        graph.getGridLabelRenderer().setHumanRounding(false);
//
//        graph.getViewport().setXAxisBoundsManual(true);
//        graph.getViewport().setMinX(4);
//        graph.getViewport().setMaxX(arrdaily1.size());
//
//        graph.getViewport().setYAxisBoundsManual(true);
//        graph.getViewport().setMinY(0);
//        graph.getViewport().setMaxY(arrdaily1.size());
//
//
//        graph.getViewport().setScrollable(true);
//        graph.getViewport().setScrollableY(true);
//        graph.getViewport().setScalable(true);
//        graph.getViewport().setScalableY(true);


        adtdaily = new Adaplistdaily(this, arrdaily);
        lvdp.setAdapter(adtdaily);
        db.close();


    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            // or highlightTouch(null) for callback to onNothingSelected(...)
            Chart.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: "
                + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }



    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        Log.i("Entry selected", e.toString());
        Log.i("LOWHIGH", "low: " + Chart.getLowestVisibleXIndex()
                + ", high: " + Chart.getHighestVisibleXIndex());

        Log.i("MIN MAX", "xmin: " + Chart.getXChartMin()
                + ", xmax: " + Chart.getXChartMax()
                + ", ymin: " + Chart.getYChartMin()
                + ", ymax: " + Chart.getYChartMax());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }

    private ArrayList<String> setXAxisValues(){
        ArrayList<String> xVals = new ArrayList<String>();
        for (int j = 0; j < arrdaily1.size(); j++) {
            xVals.add(arrdaily1.get(j).getDate());

        }
        return xVals;
    }
    private ArrayList<BarEntry> setYAxisValues(){
        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        for (int i = 0; i < arrdaily1.size(); i++) {

            yVals.add(new BarEntry((float) arrdaily1.get(i).getTime(),i ));
        }
        return yVals;
    }

    private void setData() {
        ArrayList<String> xVals = setXAxisValues();

        ArrayList<BarEntry> yVals = setYAxisValues();

        BarDataSet set1;

        // create a dataset and give it a type
        set1 = new BarDataSet(yVals, "DataSet 1");

        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        // set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.BLUE);





        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        BarData data = new BarData(xVals,dataSets);

        // set data
        Chart.setData(data);

    }




}
