package com.example.shubh.simpledecoder;

import java.util.ArrayList;

public class ContainerData {
    static public ArrayList<PassWordCust> mData=new ArrayList<>();
    static boolean b=false;

    public ContainerData(){

    }

    static public void addData(PassWordCust passWordCust){
        mData.add(passWordCust);

    }


    public static void inti() {

mData.add(new PassWordCust("Sample","Tdaal"));

        mData.add(new CodeGenerator("Sample").getWord());
    }
}
