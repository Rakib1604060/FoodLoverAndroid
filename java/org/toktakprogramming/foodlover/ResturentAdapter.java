package org.toktakprogramming.foodlover;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ResturentAdapter extends RecyclerView.Adapter<ResturentAdapter.ViewHolder> {
  ArrayList <ResturentItem>resturentItems=new ArrayList<>();
  Context context;
  OnNoteListener monNoteListener;

    public ResturentAdapter(ArrayList<ResturentItem> resturentItems,OnNoteListener onNoteListener) {
        this.resturentItems = resturentItems;

        this.monNoteListener=onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
           View V=LayoutInflater.from(parent.getContext()).inflate(R.layout.resturentitemlayour,parent,false);
           return  new ViewHolder(V,monNoteListener);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ResturentItem item=resturentItems.get(i);
        viewHolder.ResturentName.setText(item.getResturentName());
        viewHolder.charge.setText("menu:"+item.getItemNumber());
        viewHolder.tableNumber.setText(item.getTableNumber());
        viewHolder.Location.setText(item.getLocation());

    }

    @Override
    public int getItemCount() {
        return resturentItems.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {

          public TextView Location,ResturentName,tableNumber,charge;
           OnNoteListener onNoteListener;
        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            this.onNoteListener= onNoteListener;
            Location=itemView.findViewById(R.id.location);
            ResturentName=itemView.findViewById(R.id.resturentname);
            tableNumber=itemView.findViewById(R.id.tableNumber);
            charge=itemView.findViewById(R.id.delevary);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    public interface OnNoteListener{
        void onNoteClick(int position);
    }

}
