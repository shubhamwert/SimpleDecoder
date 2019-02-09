package com.example.shubh.simpledecoder;

import java.util.Random;

public class CodeGenerator {
     private PassWordCust Word;

    public CodeGenerator(String s){
        Word=new PassWordCust();
        String p= Calculate(s);
        Word.setmName(s);
        Word.setmWord(p);

    }

    private String Calculate(String s) {

        StringBuilder p = new StringBuilder();

int k;
if (!ContainerData.b){
        for (int i=0;i<s.length();i++) {
            k = s.charAt(i);
//            k = k - 65;
            k = k * k + 55;
            k = k % 26 + 65;
            char a = (char) k;
            p.append(a);
        }}
else{ for (int i=0;i<s.length();i++) {
    k = s.charAt(i);
//    k = k - 55;
    k=k+new Random().nextInt(96-55);
    k = k * k + 55*k;
    k = k % 26 + 65;
    char a = (char) k;
    p.append(a);
}


    }




        return p.toString();
    }

    public PassWordCust getWord() {
        return Word;
    }
}
