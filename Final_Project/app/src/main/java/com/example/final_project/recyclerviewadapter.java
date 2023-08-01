package com.example.final_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerviewadapter extends RecyclerView.Adapter<recyclerviewadapter.myviewholder>{
    ArrayList<recyclerviewdata> dataholder;


    public recyclerviewadapter(ArrayList<recyclerviewdata> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookingstatus_recyclerview,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
//        String tag = "";
//        Log.d(tag, "test");
    holder.name.setText(dataholder.get(position).getName());
    holder.date.setText(dataholder.get(position).getDate());
    holder.slot.setText(dataholder.get(position).getSlot());
    holder.vehicle.setText(dataholder.get(position).getVehicle());
    holder.charger.setText(dataholder.get(position).getCharger());
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name,date,slot,vehicle,charger;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name1);
            date =(TextView) itemView.findViewById(R.id.Date);
            slot = (TextView) itemView.findViewById(R.id.Slot);
            vehicle = (TextView) itemView.findViewById((R.id.Vehicle));
            charger = (TextView) itemView.findViewById(R.id.Charger);
        }
    }
}
