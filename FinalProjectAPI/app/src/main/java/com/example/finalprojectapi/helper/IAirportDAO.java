package com.example.finalprojectapi.helper;

import com.example.finalprojectapi.model.Airport;

import java.util.List;

public interface IAirportDAO {

    public boolean save (Airport airport);

    public boolean delete (int id);
    public List<Airport> listAll ();

    // Call<Airport> getData();
}

