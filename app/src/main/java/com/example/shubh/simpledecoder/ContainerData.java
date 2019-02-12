package com.example.shubh.simpledecoder;

import android.content.Context;
import android.database.Cursor;

import com.example.shubh.simpledecoder.dataHandler.mySqlhelper;

import java.util.ArrayList;

public class ContainerData {
    static public ArrayList<PassWordCust> mData=new ArrayList<>();
    static boolean b=false;
    static mySqlhelper Sqlhelper;
   static Context mContext;
    public ContainerData(Context context){

mContext=context;
    }

    static public void addData(PassWordCust passWordCust){
        mData.add(passWordCust);

    }


    public static void inti(Context context) {
Sqlhelper = new mySqlhelper(mContext);
        mContext=context;

    }
}
