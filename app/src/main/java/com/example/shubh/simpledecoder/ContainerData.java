package com.example.shubh.simpledecoder;

import java.util.ArrayList;

public class ContainerData {
    static public ArrayList<PassWordCust> mData;

    public ContainerData(){
        mData.add(new CodeGenerator("Sample").getWord());

    }

    static public void addData(PassWordCust passWordCust){
        mData.add(passWordCust);

    }



}
