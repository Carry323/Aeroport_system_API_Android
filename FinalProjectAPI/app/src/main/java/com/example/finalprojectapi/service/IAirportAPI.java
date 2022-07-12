package com.example.finalprojectapi.service;



import com.example.finalprojectapi.model.Airport;
import com.google.gson.JsonObject;

import java.util.Map;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;


public interface IAirportAPI {

    @GET("airport")
    Call<JsonObject> getData(@Query("iata") String iata, @HeaderMap Map<String, String> headers);
    Call<Airport> getData();
}
