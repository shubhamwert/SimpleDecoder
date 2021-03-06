package com.example.shubh.simpledecoder;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.shubh.simpledecoder.dataHandler.mySqlhelper;

import static com.example.shubh.simpledecoder.ContainerData.b;

public class FileAdderActivity extends AppCompatActivity {
    EditText ed;
    mySqlhelper mySqlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_adder);
        mySqlhelper=new mySqlhelper(this);
        ed = findViewById(R.id.word_pass);
        final Switch s=findViewById(R.id.lswitch);
        s.setChecked(b);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
             if(!ContainerData.b) {  AlertDialog.Builder al=new AlertDialog.Builder(FileAdderActivity.this);
                al.setMessage("The PassWord generated will be completly random. once lost password cannot be recovered.click okay to continue");
                al.setPositiveButton("okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        b = true;
                        s.setChecked(b);
                    }

                });
                al.setNegativeButton("Go back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(FileAdderActivity.this,"simple coder activated",Toast.LENGTH_SHORT).show();
                        s.setChecked(b);

                    }
                });
            AlertDialog ald=al.create();
            ald.show();
             }
            else{
                b=false;
                 s.setChecked(b);
             }

        }});


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    public void submit(View view) {

        String s=ed.getText().toString().trim();
        if (s.length()>0){
        AddData(new CodeGenerator(s).getWord());


        finish();}
        else {Toast.makeText(this,"No Word to Add Found",Toast.LENGTH_SHORT).show();
        }
    }
    public void AddData(PassWordCust passWordCust){
        boolean insertData=mySqlhelper.insertData(passWordCust);
        if (insertData){
            Toast.makeText(this,"DATA UPDATED SUCCESSFULLY",Toast.LENGTH_SHORT).show();
        }
        else {            Toast.makeText(this,"DATA UPDATE FAILED",Toast.LENGTH_SHORT).show();
        }
    }
}
