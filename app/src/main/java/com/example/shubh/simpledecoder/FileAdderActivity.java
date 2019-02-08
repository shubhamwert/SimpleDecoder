package com.example.shubh.simpledecoder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FileAdderActivity extends AppCompatActivity {
    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_adder);

        ed = findViewById(R.id.word_pass);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Added Word",Toast.LENGTH_SHORT).show();

    }

    public void submit(View view) {

        String s=ed.getText().toString().trim();

        ContainerData.mData.add(new CodeGenerator(s).getWord());

        Toast.makeText(this,"Adding to directory word = "+s,Toast.LENGTH_SHORT).show();

        finish();
    }
}
