package com.example.shubh.simpledecoder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.shubh.simpledecoder.dataHandler.mySqlhelper;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
RecyclerList Adapter;
mySqlhelper mySqlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySqlhelper=new mySqlhelper(this);
        ContainerData.inti(getApplicationContext());
        recyclerView=findViewById(R.id.mRecyler);
        Adapter=new RecyclerList();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);


        recyclerView.setAdapter(Adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
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


refreshList();

    }
public void refreshList(){

    Cursor res=mySqlhelper.GetAllData();


    if (res.getCount()==0){        Toast.makeText(this,"NO DATA FOUND",Toast.LENGTH_SHORT).show();

        return;
    }

    ContainerData.mData.clear();
    while (res.moveToNext()){

        ContainerData.mData.add(new PassWordCust(res.getString(1),res.getString(2)));


    }

    Adapter.notifyDataSetChanged();


}


    @Override
    protected void onPostResume() {
        super.onPostResume();
        for (int i=0;i<ContainerData.mData.size();i++){
            mySqlhelper.insertData(ContainerData.mData.get(i));
        }
        refreshList();

        Adapter.notifyDataSetChanged();

    }

    public void next(View view) {
        Intent i=new Intent(MainActivity.this,FileAdderActivity.class);
        startActivity(i);
    }
    public interface ClickListener{
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
