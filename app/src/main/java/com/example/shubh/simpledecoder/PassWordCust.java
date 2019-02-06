package com.example.shubh.simpledecoder;

public class PassWordCust {
   private String mName;
   private String mWord;

    public PassWordCust(String Name,String Word) {
        super();
        Name= mName;
        Word=mWord;
    }

    public PassWordCust() {
        super();
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmWord(String mWord) {
        this.mWord = mWord;
    }

    public String getmName() {
        return mName;
    }

    public String getmWord() {
        return mWord;
    }
}
