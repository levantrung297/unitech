package com.htnsoft.unitech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText edtUserName, edtUserPassWord;
    Button btnUserLogin, btnUserRegister;
    String url="http://chuan03.com/unitech/UserLogin.php";
    boolean CheckEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtUserPassWord = (EditText) findViewById(R.id.edtUserPassWord);
        btnUserLogin = (Button) findViewById(R.id.btnUserLogin);
        btnUserRegister = (Button) findViewById(R.id.btnUserRegister);

        //Login

        btnUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditTextEmptyOrNot();
                if (CheckEditText){
                    UserLoginFunction(url);
                } else {
                    Toast.makeText(LoginActivity.this, "Bạn vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Register
        btnUserRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SiginupActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void CheckEditTextEmptyOrNot(){
        String UserName = edtUserName.getText().toString();
        String UserPassWord = edtUserPassWord.getText().toString();

        if(TextUtils.isEmpty(UserName) || TextUtils.isEmpty(UserPassWord)){
            CheckEditText = false;
        } else {
            CheckEditText = true;
        }
    }

    private void UserLoginFunction(String url){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_email",edtUserName.getText().toString().trim());
                params.put("user_password", edtUserPassWord.getText().toString().trim());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
