package com.example.shubh.simpledecoder.dataHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shubh.simpledecoder.PassWordCust;

public class mySqlhelper extends SQLiteOpenHelper {

public static final String DATABASE_NAME = "CODES.db";
    public static final String TABLE_NAME = "WORD_CODE";
    public static final String ID="id";
    public static final String WORD = "word";
    public static final String CODE = "code";


    public mySqlhelper(Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,MARKS INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

public boolean insertData(PassWordCust passWordCust){
SQLiteDatabase db=this.getWritableDatabase();
    ContentValues contentValues=new ContentValues();
contentValues.put(WORD,passWordCust.getmName());
contentValues.put(CODE,passWordCust.getmWord());
    long result=db.insert(TABLE_NAME,null,contentValues);
    return result != -1;


}

public Cursor getAllData(){
SQLiteDatabase db=this.getWritableDatabase();
    return db.rawQuery("select from * "+TABLE_NAME,null);
}

public boolean updateData(PassWordCust passWordCust){
    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues contentValues=new ContentValues();
    contentValues.put(WORD,passWordCust.getmName());
    contentValues.put(CODE,passWordCust.getmWord());

    db.update(TABLE_NAME,contentValues,"id = ?",new String[]{ ID } );

    return true;
}
    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();

        return db.delete(TABLE_NAME,"id = ? ",new String[]{ id });
    }

}
