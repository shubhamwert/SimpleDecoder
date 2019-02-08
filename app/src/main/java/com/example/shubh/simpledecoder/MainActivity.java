package com.example.shubh.simpledecoder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
RecyclerList Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContainerData.inti();
        recyclerView=findViewById(R.id.mRecyler);
        Adapter=new RecyclerList();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(Adapter);

        prepareData();



    }

    private void prepareData() {
ContainerData.mData.add(new CodeGenerator("hello").getWord());
Adapter.notifyDataSetChanged();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Adapter.notifyDataSetChanged();
    }

    public void next(View view) {
        Intent i=new Intent(MainActivity.this,FileAdderActivity.class);
        startActivity(i);
    }
}
