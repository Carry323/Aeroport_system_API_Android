package com.example.finalprojectapi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalprojectapi.R;
import com.example.finalprojectapi.ThirdActivity;
import com.example.finalprojectapi.model.Airport;

import java.util.ArrayList;
import java.util.List;

public class AirportAdapter extends RecyclerView.Adapter<AirportAdapter.MyViewHolder>{

    private ArrayList<Airport> airportList;

    public AirportAdapter(ArrayList<Airport> airportList){
        this.airportList = airportList;
    }

    public AirportAdapter(Context applicationContext, ThirdActivity thirdActivity, List<Airport> allAirports) {
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView location,  nameAirport, phone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            location = itemView.findViewById(R.id.textViewLocation);
            nameAirport = itemView.findViewById(R.id.textViewName);
            phone = itemView.findViewById(R.id.textViewPhone);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_listairports, parent, false);

        return new MyViewHolder(listItem);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Airport airport = airportList.get(position);

        holder.location.setText(airport.getCity());

        holder.phone.setText(airport.getPhone());
        holder.nameAirport.setText(airport.getNameAirport());

        if (airport.getId() % 2 == 0){


            holder.location.setTextColor(Color.parseColor("#663399"));
            holder.phone.setTextColor(Color.parseColor("#663399"));
            holder.nameAirport.setTextColor(Color.parseColor("#663399"));

        }

    }

    @Override
    public int getItemCount() {
        //returning the number of rows
        return airportList.size();
    }

}