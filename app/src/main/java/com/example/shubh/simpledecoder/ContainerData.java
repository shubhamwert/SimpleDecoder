package com.example.shubh.simpledecoder;

import java.util.ArrayList;

public class ContainerData {
    static public ArrayList<PassWordCust> mData=new ArrayList<>();

    public ContainerData(){
        mData.add(new CodeGenerator("Sample").getWord());

    }

    static public void addData(PassWordCust passWordCust){
        mData.add(passWordCust);

    }


    public static void inti() {
mData.add(new PassWordCust("Sample","Tdaal"));

        mData.add(new CodeGenerator("Sample").getWord());
    }
}
