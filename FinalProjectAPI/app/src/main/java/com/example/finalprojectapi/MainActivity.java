package com.example.finalprojectapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalprojectapi.helper.SQLiteManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextUserName, editTextPassword;
    Button btnLogin, btnRegister;
    SQLiteManager sqLiteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        sqLiteManager = new SQLiteManager(this);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.btnLogin: {
                btnLoginClick();
                break;
            }
            case R.id.btnRegister: {
                btnRegisterClick();
                break;
            }
            default: {
                break;
            }
        }

    }


    //LOGIN
    private void btnLoginClick() {


        try {

            //equals("")
            if (editTextUserName.getText().toString().isEmpty() || editTextPassword.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please, fill all the fields", Toast.LENGTH_LONG).show();
            }

            else {

                //String hashPass = "12345";
                //String bcryptHashString = BCrypt.withDefaults().hashToString(12, hashPass.toCharArray());

                //BCrypt.Result result = BCrypt.verifyer().verify(editTextPassword.getText().toString().toCharArray(), bcryptHashString);

                Boolean checkUserPass = sqLiteManager.checkPassword(editTextUserName.getText().toString(), editTextPassword.getText().toString());
                if (checkUserPass==true) { // && result.verified
                    Toast.makeText(getApplicationContext(), "SignIn successful", Toast.LENGTH_LONG).show();
                    Intent thirdActivity = new Intent(this, ThirdActivity.class);
                    startActivity(thirdActivity);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_LONG).show();
                }
            }
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    //REGISTER
    private void btnRegisterClick() {

        Intent secondActivity = new Intent(getApplicationContext(), SecondActivity.class);
        startActivity(secondActivity);
        finish();
    }
}