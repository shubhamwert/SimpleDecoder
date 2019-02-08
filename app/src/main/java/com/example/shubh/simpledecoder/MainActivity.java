package com.example.shubh.simpledecoder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
RecyclerList Adapter;
    Switch s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContainerData.inti();
        recyclerView=findViewById(R.id.mRecyler);
        Adapter=new RecyclerList();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);

        s = findViewById(R.id.mswitch);
        s.setChecked(ContainerData.b);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ContainerData.b = !ContainerData.b;
            }
        });

        recyclerView.setAdapter(Adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,recyclerView,new ClickListener(){


            @Override
            public void onClick(View view, int position) {
            ContainerData.mData.remove(position);
            Adapter.notifyDataSetChanged();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        prepareData();



    }

    private void prepareData() {
ContainerData.mData.add(new CodeGenerator("hello").getWord());
Adapter.notifyDataSetChanged();
s.setChecked(ContainerData.b);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Adapter.notifyDataSetChanged();
        s.setChecked(ContainerData.b);
    }

    public void next(View view) {
        Intent i=new Intent(MainActivity.this,FileAdderActivity.class);
        startActivity(i);
    }
    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);}

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
