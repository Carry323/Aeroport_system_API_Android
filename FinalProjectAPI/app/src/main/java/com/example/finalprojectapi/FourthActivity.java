package com.example.finalprojectapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectapi.helper.AirportDAO;
import com.example.finalprojectapi.model.Airport;
import com.example.finalprojectapi.service.IAirportAPI;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FourthActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher { //implements View.OnClickListener, TextWatcher


    TextView textViewCityAirport, textViewNameOfAirport, textViewPhoneAirport;

    EditText editTextIataAirport;
    Button buttonInfoAirport, buttonSave;
    ConstraintLayout layoutInfoContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        initialize();
    }

    private void initialize() {

        textViewCityAirport = findViewById(R.id.textViewCityAirport);
        textViewNameOfAirport = findViewById(R.id.textViewNameOfAirport);
        textViewPhoneAirport = findViewById(R.id.textViewPhoneAirport);

        editTextIataAirport = findViewById(R.id.editTextIataAirport);
        buttonInfoAirport = findViewById(R.id.buttonInfoAirport);
        buttonSave = findViewById(R.id.buttonSave);

        layoutInfoContainer = findViewById(R.id.layoutInfoContainer);
        layoutInfoContainer.setVisibility(View.INVISIBLE);


        buttonInfoAirport.setOnClickListener(this);
        buttonSave.setOnClickListener(this);

        editTextIataAirport.addTextChangedListener(this);


    }

    @Override
    public void onClick(View view) {

        int chose = view.getId();

        switch (chose) {
            case R.id.buttonInfoAirport:

                buttonInfoAirportClick();
                break;
            case R.id.buttonSave:

                buttonSaveClick();
                break;

            default:
                break;


        }


    }

    private void buttonInfoAirportClick() {

        layoutInfoContainer.setVisibility(View.INVISIBLE);
        if (editTextIataAirport.getText().toString().length() == 3) {

            callAirportApi(editTextIataAirport.getText().toString(), layoutInfoContainer);


        } else {


            Toast.makeText(getBaseContext(), "Please, enter a valid IATA code", Toast.LENGTH_SHORT).show();
        }
    }

    private void callAirportApi(String iata, ConstraintLayout layoutInfoContainer) {
        //'https://airport-info.p.rapidapi.com/airport',
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://airport-info.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IAirportAPI iAirportAPI = retrofit.create(IAirportAPI.class);

        Map<String, String> header = new HashMap<>();
        header.put("x-rapidapi-host", "airport-info.p.rapidapi.com");
        header.put("x-rapidapi-key", "873065ebe5msh5fdcd5cb5334a5bp1a833fjsncc0bd00dfcaa");


        Call<JsonObject> call = iAirportAPI.getData(iata, header);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.i("Response Code", String.valueOf(response.code()));
                if (response.code() != 200) {
                    Toast.makeText(getBaseContext(), "no response/error from server", Toast.LENGTH_LONG).show();
                    return;
                }

                //Converting info received inside body to Json format
                String jsonString = new Gson().toJson(response.body());
                Log.i("Json response", jsonString);

                //Using JsonParser to convert our String to JsonObject
                JsonParser parser = new JsonParser();
                JsonObject json = (JsonObject) parser.parse(jsonString);

                try {
                    textViewCityAirport.setText(json.getAsJsonObject().get("location").getAsString());
                    textViewNameOfAirport.setText(json.getAsJsonObject().get("name").getAsString());
                    textViewPhoneAirport.setText(json.getAsJsonObject().get("phone").getAsString());

                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Error fetching data" + e.toString(), Toast.LENGTH_LONG).show();
                }

                if (layoutInfoContainer != null) {
                    layoutInfoContainer.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getBaseContext(), "something is wrong" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void buttonSaveClick() {


        AirportDAO airportDAO = new AirportDAO(getApplicationContext());


//
//        Boolean newAirport = (editTextIataAirport.getText().toString().length() == 0);
//
//        int id = 0;
//        if(!newAirport) {
//            id = Integer.valueOf(editTextIataAirport.getText().toString());
//        }

        Airport airport = new Airport(0, textViewCityAirport.getText().toString(), textViewPhoneAirport.getText().toString(), textViewNameOfAirport.getText().toString());



        //  if ((newAirport && airportDAO.save(airport))  ){

        if (airportDAO.save(airport)){
            Toast.makeText(getApplicationContext(), "Record success saved", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Problem to saved record", Toast.LENGTH_LONG).show();
        }
        Intent thirdActivity = new Intent(getApplicationContext(), ThirdActivity.class);
        startActivity(thirdActivity);
        finish();
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        layoutInfoContainer.setVisibility(View.INVISIBLE);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
