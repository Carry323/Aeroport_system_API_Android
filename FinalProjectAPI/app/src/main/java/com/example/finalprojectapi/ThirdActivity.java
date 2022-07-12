package com.example.finalprojectapi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectapi.adapter.AirportAdapter;
import com.example.finalprojectapi.helper.AirportDAO;
import com.example.finalprojectapi.model.Airport;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private Button buttonAdd, buttonCancel;
    private ArrayList<Airport> airportList = new ArrayList<Airport>();
    TextView textView7;
   // ConstraintLayout layoutInfoContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        initialize();
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,  LinearLayout.VERTICAL));
        this.loadAirport();
    }

    private void initialize() {

        recyclerView = findViewById(R.id.recyclerView);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonCancel = findViewById(R.id.buttonCancel);

        buttonAdd.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
       // layoutInfoContainer = findViewById(R.id.layoutInfoContainer);
      //  layoutInfoContainer.setVisibility(View.VISIBLE);
        textView7 = findViewById(R.id.textView7);
        textView7.setVisibility(View.VISIBLE);

        loadAirport();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        AirportAdapter adapter = new AirportAdapter(airportList);
        recyclerView.setAdapter(adapter);





        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                        AlertDialog.Builder dialog = new AlertDialog.Builder(ThirdActivity.this);
                        dialog.setTitle("Delete?");
                        dialog.setMessage("Are you sure you want to delete?");

                        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                AirportDAO airportDAO = new AirportDAO(getApplicationContext());
                                AirportAdapter adapter = new AirportAdapter(airportList);
                                //    if(airportDAO.delete(position) == true)


                                if (airportDAO.delete(position) == true) {
                                    Toast.makeText(getApplicationContext(), "airport success deleted", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Can not delete", Toast.LENGTH_LONG).show();
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
                        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // nothing happens...
                            }
                        });
                        dialog.create();
                        dialog.show();
                    }


                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }
        ));

    }


    public void loadAirport(){
//        airportList.add(new Airport(10, "Montreal", "Trudeau", "123456"));
//        airportList.add(new Airport(9, "Toronto", "Pirson", "654321"));
      //  layoutInfoContainer.setVisibility(View.INVISIBLE);
        textView7.setVisibility(View.INVISIBLE);
        AirportDAO airportDAO = new AirportDAO(getApplicationContext());
        airportList = airportDAO.listAll();
     }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.buttonAdd: {
                btnAddClick();

                break;
            }

            case R.id.buttonCancel: {
                btnCancelClick();

                break;
            }
            default: {
                break;
            }



        }

    }

    private void btnCancelClick() {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
        finish();
    }

    private void btnAddClick() {
        Intent fourthActivity = new Intent(getApplicationContext(), FourthActivity.class);
        startActivity(fourthActivity);

    }


}