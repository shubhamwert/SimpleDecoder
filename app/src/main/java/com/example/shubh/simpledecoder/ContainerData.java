package com.example.shubh.simpledecoder;

import java.util.ArrayList;

public class ContainerData {
    static public ArrayList<PassWordCust> mData;

    public ContainerData(){

    }

    static public void addData(PassWordCust passWordCust){
        mData.add(passWordCust);

    }



}
