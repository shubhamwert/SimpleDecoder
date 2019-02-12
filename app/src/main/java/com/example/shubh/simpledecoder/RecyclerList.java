package com.example.shubh.simpledecoder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

public class RecyclerList extends RecyclerView.Adapter<RecyclerList.ViewHolder> {
    private Context mContext;
private int lastPosition=0;
    public RecyclerList() {
        super();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext=viewGroup.getContext();
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View v=inflater.inflate(R.layout.recycler_colomn,viewGroup,false);
        v.setBackgroundColor(Color.red(22));
        v.setBackgroundColor(Color.green(55));
        v.setPadding(3,3,3,3);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
    viewHolder.Word.setText(ContainerData.mData.get(i).getmName());
    viewHolder.Code.setText(ContainerData.mData.get(i).getmWord());
    viewHolder.img.setMaxWidth(viewHolder.img.getHeight());
        setAnimation(viewHolder.itemView, i);



    }
    @Override
    public void onViewDetachedFromWindow(@NonNull final RecyclerList.ViewHolder holder)
    {
        ((ViewHolder)holder).clearAnimation();
    }

    private void setAnimation(View viewToAnimate, int position)
    {

        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            animation.setInterpolator(mContext,android.R.anim.bounce_interpolator);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return ContainerData.mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Word;
        TextView Code;
        ImageButton img;
        View mRootLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mRootLayout=itemView;
            Word = itemView.findViewById(R.id.rec_Word);
            Code=itemView.findViewById(R.id.rec_pass_word);
            img=itemView.findViewById(R.id.del);

        }
        public void clearAnimation()
        {
            mRootLayout.clearAnimation();
        }
    }




}
