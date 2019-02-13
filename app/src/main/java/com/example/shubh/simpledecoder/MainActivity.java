package com.example.shubh.simpledecoder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.shubh.simpledecoder.dataHandler.mySqlhelper;

public class MainActivity extends AppCompatActivity {
        RecyclerView recyclerView;
        RecyclerList Adapter;
        mySqlhelper mySqlhelper;
        String TAG="DATA";

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
        refreshList();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,recyclerView,new ClickListener(){


            @Override
            public void onClick(View view, int position) {
            mySqlhelper.deleteData(""+position);
            ContainerData.mData.remove(position);
            Adapter.notifyDataSetChanged();
            refreshList();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        })); }
public void refreshList(){
    Cursor data=mySqlhelper.GetAllData();
    int i=0;
    ContainerData.mData.clear();
    while (data.moveToNext()){
        ContainerData.mData.add(new PassWordCust(data.getString(1),data.getString(2)));
        Log.d(TAG, "refreshList : "+data.getString(1)+" WORD "+data.getString(2));
        Log.d(TAG, "mData List "+ContainerData.mData.get(i).getmName()+"mData List "+ContainerData.mData.get(i).getmWord());
        i++;

    }
    Adapter.notifyDataSetChanged();
    data.close();}
    @Override
    protected void onPostResume() {super.onPostResume();
        refreshList();
        Adapter.notifyDataSetChanged();}
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
