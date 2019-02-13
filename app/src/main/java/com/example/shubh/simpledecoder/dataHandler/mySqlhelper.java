package com.example.shubh.simpledecoder.dataHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.shubh.simpledecoder.PassWordCust;

public class mySqlhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="student_table";
    public static final String ID="id";
    public static final String NAME="name";
    public static final String WORD="WORD";
    public static final String TAG="FILE STORED";


    public mySqlhelper( Context context) {
        super(context,DATABASE_NAME,null,1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" TEXT,"+WORD+" INT) ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(PassWordCust passWordCust){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,passWordCust.getmName());
        contentValues.put(WORD,passWordCust.getmWord());
        Log.d(TAG, "updateData: "+contentValues.get(NAME)+" WORd "+contentValues.get(WORD));
        long result=db.insert(TABLE_NAME,null,contentValues);
        return result != -1;



    }
    public Cursor GetAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
    }
    public boolean updateData(String id,PassWordCust passWordCust){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,passWordCust.getmName());
        contentValues.put(WORD,passWordCust.getmWord());
        db.update(TABLE_NAME,contentValues,"id = ?",new String[]{ id } );

        return true;

    }
    public boolean deleteData(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        try
        {
            int result=db.delete(TABLE_NAME,"name = ? ",new String[]{ name });
            if(result>0)
            {
                return true;
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }




}
