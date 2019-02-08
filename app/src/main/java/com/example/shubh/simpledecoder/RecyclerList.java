package com.example.shubh.simpledecoder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

public class RecyclerList extends RecyclerView.Adapter<RecyclerList.ViewHolder> {
    private Context mContext;

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
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
    viewHolder.Word.setText(ContainerData.mData.get(i).getmName());
    viewHolder.Code.setText(ContainerData.mData.get(i).getmWord());
    viewHolder.img.setMaxWidth(viewHolder.img.getHeight());


    }

    @Override
    public int getItemCount() {
        return ContainerData.mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Word;
        TextView Code;
        ImageButton img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Word = itemView.findViewById(R.id.rec_Word);
            Code=itemView.findViewById(R.id.rec_pass_word);
            img=itemView.findViewById(R.id.del);

        }
    }




}
