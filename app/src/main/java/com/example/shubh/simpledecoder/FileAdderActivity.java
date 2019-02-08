package com.example.shubh.simpledecoder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class FileAdderActivity extends AppCompatActivity {
    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_adder);

        ed = findViewById(R.id.word_pass);
        Switch s=findViewById(R.id.lswitch);
        s.setChecked(ContainerData.b);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ContainerData.b = !ContainerData.b;

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    public void submit(View view) {

        String s=ed.getText().toString().trim();
        if (!s.equals("")){ContainerData.mData.add(new CodeGenerator(s).getWord());

        Toast.makeText(this,"New Word "+s+" Added",Toast.LENGTH_SHORT).show();

        finish();}
        else {Toast.makeText(this,"No Word to Add Found",Toast.LENGTH_SHORT).show();
        }
    }
}
