package com.htnsoft.unitech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class UserLoginActivity extends AppCompatActivity {

    EditText edtUserName, edtUserPassword;
    Button btnUserLogin, btnUserRegister;

    String passwordHolder, emailHolder;
    String finalResult;
    String HttpURL = "";
    Boolean CheckEditText;
    HashMap<String, String> hashMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
