package com.thpa.a9019.fitnessmore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.thpa.a9019.fitnessmore.model.Daily;

import java.util.ArrayList;

/**
 * Created by COM on 10/4/2560.
 */

public class DBHelper extends SQLiteOpenHelper {

    public String sql;
    private static final String DATABASE_NAME = "FitBase2.db";
    private static final int DB_VERSION = 4;

    public static final String TABLE_NAME1 = "dailyPractice";
    public static final String TABLE_NAME = "training";

    //Table training
    public static final String TRAINING_COLUMN_NAME = "name";
    public static final String TRAINING_COLUMN_URLPHOTO = "photo";
    public static final String TRAINING_COLUMN_TYPE = "type";
    public static final String TRAINING_COLUMN_URLTYPEPHOTO = "tphoto";
    public static final String TRAINING_COLUMN_DESCRIPT = "des";
    public static final String TRAINING_COLUMN_VDO = "video";

    //Table DailyPractice
    public static final String COL_DATE = "date";
    public static final String COL_NAME_PRACTICE = "pracname";
    public static final String COL_TIME_PRACTICE = "practime";
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private String DROP_USER_TABLE1 = "DROP TABLE IF EXISTS " + TABLE_NAME1;




    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        sql = "CREATE TABLE " +
                TABLE_NAME +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                TRAINING_COLUMN_NAME +
                " TEXT," +
                TRAINING_COLUMN_URLPHOTO +
                " TEXT," +
                TRAINING_COLUMN_TYPE +
                " TEXT," +
                TRAINING_COLUMN_URLTYPEPHOTO +
                " TEXT," +
                TRAINING_COLUMN_DESCRIPT +
                " TEXT," +
                TRAINING_COLUMN_VDO +
                " TEXT"+")";

        db.execSQL(sql);


        // db.execSQL("create table training" + "(id integer primary key, name text, photo text, type text, des text, video text)");
        Log.d("MyDB", "Created Table1");
        db.execSQL("create table " + TABLE_NAME1
                + "  (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_DATE + " text, "
                + COL_NAME_PRACTICE + " text, "
                + COL_TIME_PRACTICE + " integer);");
        Log.d("MyDB", "Created Table2");

    }

//    private void CheckDatabaseSQLite() {
//        SQLiteDatabase db =
//        String count = "SELECT count(*) FROM table";
//        Cursor mcursor = db.rawQuery(count, null);
//        mcursor.moveToFirst();
//        int icount = mcursor.getInt(0);
//        if(icount>0)
////leave
//else
////populate table
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_USER_TABLE1);

        Log.d("MyDB", "Re-Create Table");
        onCreate(db);
    }


    public boolean checkFromDate(String date, String pracName) {

        String[] columns = {
                COL_TIME_PRACTICE};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_DATE + " = ?" + " AND " + COL_NAME_PRACTICE + " = ?";
        String[] selectionArgs = {date, pracName};
        Cursor cursor = db.query(TABLE_NAME1,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;

    }

    public void Updatetime(String date, String pracName, int time) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_TIME_PRACTICE, time);
        String selection = "UPDATE "+TABLE_NAME1+" SET "+COL_TIME_PRACTICE+" = ?"+" WHERE "+COL_DATE + " = ?" + " AND " + COL_NAME_PRACTICE + " = ?";
        db.rawQuery(selection,new String[]{String.valueOf(time),date, pracName});

        db.close();
    }


    public ArrayList<Daily> getAlldataPractice() {
        ArrayList<Daily> daily = new ArrayList<Daily>();
        SQLiteDatabase mDb = getWritableDatabase();
        Cursor mCursor = mDb.rawQuery("SELECT * FROM " + TABLE_NAME1, null);
        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()) {
            int id = mCursor.getInt((mCursor.getColumnIndex("_id")));
            String date = mCursor.getString(mCursor.getColumnIndex(COL_DATE));
            String name = mCursor.getString(mCursor.getColumnIndex(COL_NAME_PRACTICE));
            int time = mCursor.getInt(mCursor.getColumnIndex(COL_TIME_PRACTICE));
            daily.add(new Daily(id, date, name, time));
            mCursor.moveToNext();
        }
        mCursor.close();
        mDb.close();
        return daily;
    }

    public ArrayList<Daily> getAlldataPractice1() {
        ArrayList<Daily> daily = new ArrayList<Daily>();
        SQLiteDatabase mDb = getWritableDatabase();
        Cursor mCursor = null;
        mCursor = mDb.rawQuery("SELECT " + COL_DATE + ", SUM( " + COL_TIME_PRACTICE + ") AS my_sum FROM " + TABLE_NAME1 + " GROUP BY " + COL_DATE + " ORDER BY " + COL_DATE + " ASC ", null);
        if (mCursor != null) {
            mCursor.moveToFirst();
            while (!mCursor.isAfterLast()) {

                String date = mCursor.getString(mCursor.getColumnIndex(COL_DATE));
                int time = mCursor.getInt(mCursor.getColumnIndex("my_sum"));
                daily.add(new Daily(date, time));
                mCursor.moveToNext();
            }
        }


        mCursor.close();
        mDb.close();
        return daily;
    }


    public int getNumberofData() {
        int count = 0;
        SQLiteDatabase mDb = getWritableDatabase();
        Cursor mCursor = mDb.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()) {
            count++;
            mCursor.moveToNext();
        }
        mCursor.close();
        mDb.close();
        return count;
    }


    public boolean InsertTrain(String name, String photo, String type, String tphoto, String des, String vid) {
        SQLiteDatabase mdb = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRAINING_COLUMN_NAME, name);
        contentValues.put(TRAINING_COLUMN_URLPHOTO, photo);
        contentValues.put(TRAINING_COLUMN_TYPE, type);
        // contentValues.put(TRAINING_COLUMN_URLTYPEPHOTO , tphoto);
        contentValues.put(TRAINING_COLUMN_DESCRIPT, des);
        // contentValues.put(TRAINING_COLUMN_VDO , vid);
        mdb.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertDailyPractice(String date, String name, int time) {
        SQLiteDatabase mdb = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_DATE, date);
        contentValues.put(COL_NAME_PRACTICE, name);
        contentValues.put(COL_TIME_PRACTICE, time);
        mdb.insert(TABLE_NAME1, null, contentValues);
        return true;
    }


    public ArrayList<String> getAllTraining(String type) {
        ArrayList<String> array_list = new ArrayList<>();
        String trainname = null;

        SQLiteDatabase mdb = getReadableDatabase();
        Cursor res = mdb.rawQuery("select name from " + TABLE_NAME + " where " + TRAINING_COLUMN_TYPE + " = ?", new String[]{type});
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            trainname = new String(res.getString(res.getColumnIndex(TRAINING_COLUMN_NAME)));
            array_list.add(trainname);
            res.moveToNext();
        }
        res.close();
        mdb.close();
        return array_list;
    }


    public String getTrainingColumnType(String name) {

        String type = null;

        SQLiteDatabase mdb = getReadableDatabase();
        Cursor res = mdb.rawQuery("select * from " + TABLE_NAME + " where " + TRAINING_COLUMN_NAME + "= ?", new String[]{name});
        res.moveToFirst();
        type = res.getString(res.getColumnIndex(TRAINING_COLUMN_TYPE));
        res.close();
        mdb.close();
        return type;
    }

    public String getTrainingColumnDescript(String name) {


        String des = null;
        SQLiteDatabase mdb = getReadableDatabase();
        Cursor res = mdb.rawQuery("select * from " + TABLE_NAME + " where " + TRAINING_COLUMN_NAME + " = ?", new String[]{name});
        res.moveToFirst();
        des = res.getString(res.getColumnIndex(TRAINING_COLUMN_DESCRIPT));

        res.close();
        mdb.close();
        return des;
    }

    public String getTrainingColumnUrlphoto(String name) {
        String uphoto = null;
        SQLiteDatabase mdb = getReadableDatabase();
        Cursor res = mdb.rawQuery("select * from training where name = ?", new String[]{name});
        res.moveToFirst();
        uphoto = res.getString(res.getColumnIndex(TRAINING_COLUMN_URLPHOTO));
        res.close();
        mdb.close();
        return uphoto;
    }

    public String getTrainingColumnTUrlphoto(String name) {
        String uphoto ;
        String[] columns = {
                TRAINING_COLUMN_URLTYPEPHOTO
        };
        String selection = TRAINING_COLUMN_NAME + " = ?";
        String[] selectionArgs = {name};
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);
        cursor.moveToFirst();
        uphoto = cursor.getString(cursor.getColumnIndex(TRAINING_COLUMN_URLTYPEPHOTO));
        cursor.close();
        db.close();
        return uphoto;
    }

    public ArrayList<String> getAllTrainForSpinner() {
        ArrayList<String> array_list = new ArrayList<String>();
        String trainname;

        SQLiteDatabase mdb = getReadableDatabase();
        Cursor res = mdb.rawQuery("select name from training ", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            trainname = new String(res.getString(res.getColumnIndex(TRAINING_COLUMN_NAME)));
            array_list.add(trainname);
            res.moveToNext();
        }
        res.close();
        mdb.close();
        return array_list;
    }
    public void DeleteDate(String date, String pracName, int time) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = COL_DATE + " = ?" + " AND " + COL_NAME_PRACTICE + " = ?" + " AND " + COL_TIME_PRACTICE + " = ?";
        db.delete(TABLE_NAME1, selection,
                new String[]{date, pracName, String.valueOf(time)});
        db.close();
    }
    public void DeleteDate2(String date, String pracName) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = COL_DATE + " = ?" + " AND " + COL_NAME_PRACTICE + " = ?" ;
        db.delete(TABLE_NAME1, selection,
                new String[]{date, pracName});
        db.close();
    }

}
