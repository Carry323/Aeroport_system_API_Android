package com.example.finalprojectapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalprojectapi.helper.SQLiteManager;
import com.example.finalprojectapi.helper.UserDAO;
import com.example.finalprojectapi.model.User;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword1;
    Button btnSave, btnReturn;
    SQLiteManager sqLiteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initialize();
    }


    private void initialize() {

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword1 = findViewById(R.id.editTextPassword);

        btnSave = findViewById(R.id.btnSave);
        btnReturn = findViewById(R.id.btnReturn);
        sqLiteManager = new SQLiteManager(this);

        btnSave.setOnClickListener(this);
        btnReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.btnSave: {
                btnSaveClick();
                break;
            }
            case R.id.btnReturn: {
                btnReturnClick();
                break;
            }
            default: {
                break;
            }
        }
    }

    // RETURN
    private void btnReturnClick() {

        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
        finish();
    }


    //SAVE
    private void btnSaveClick() {

        try {
            Boolean checkUser = sqLiteManager.checkUsername(editTextEmail.getText().toString());

            if (editTextFirstName.getText().toString().length() >= 1) {
                if (editTextLastName.getText().toString().length() >= 1) {
                    if (editTextEmail.getText().toString().matches("^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+$")) {
                        if (editTextPassword1.getText().toString().length() >= 8) {
                            if (checkUser == false) {
                                UserDAO userDAO = new UserDAO(getApplicationContext());
                                User user = new User(0, editTextFirstName.getText().toString(), editTextLastName.getText().toString(), editTextEmail.getText().toString(), editTextPassword1.getText().toString());

                                if (userDAO.save(user)) {
                                    Toast.makeText(getApplicationContext(), "User saved", Toast.LENGTH_LONG).show();
                                    Intent mainActivity = new Intent(this, MainActivity.class);
                                    startActivity(mainActivity);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "User not saved", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "User with this email already exists", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Password should contain 8 characters or more", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Email has not correct format", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Last name can't be empty", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "First name can't be empty", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }
}