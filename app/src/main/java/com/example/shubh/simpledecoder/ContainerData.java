package com.example.shubh.simpledecoder;

import android.content.Context;

import java.util.ArrayList;

public class ContainerData {
    static public ArrayList<PassWordCust> mData=new ArrayList<>();
    static boolean b=false;

   static Context mContext;
    public ContainerData(Context context){


mContext=context;
    }

    static public void addData(PassWordCust passWordCust){
mData.add(passWordCust);

    }


    public static void inti(Context context) {

        mContext=context;

    }


}
