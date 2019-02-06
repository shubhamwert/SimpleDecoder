package com.example.shubh.simpledecoder;

import android.content.Intent;

public class CodeGenerator {
     PassWordCust Word;

    public CodeGenerator(String s){
        Word=new PassWordCust();
        String p= Calculate(s);
        Word.setmName(s);
        Word.setmWord(p);

    }

    private String Calculate(String s) {
        String p="";

int k;
        for (int i=0;i<s.length();i++){
            k= s.charAt(i);
            k=k-65;
            k=k*k+55;
            k=k%26+65;
           char a = (char)k;
           p=p+a;




        }

        return p;
    }

    public PassWordCust getWord() {
        return Word;
    }
}
