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

public class SiginupActivity extends AppCompatActivity {

    EditText edtUserNameRegister, edtUserEmailRegister, edtUserPassWordRegister;
    Button btnRegister;
    String url ="chuan03.com/unitech/UserRegistration.php";
    boolean CheckEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siginup);

        edtUserNameRegister = (EditText) findViewById(R.id.edtUserNameRegister);
        edtUserEmailRegister = (EditText) findViewById(R.id.edtUserEmailRegister);
        edtUserPassWordRegister = (EditText) findViewById(R.id.edtUserPassWordRegister);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserName = edtUserNameRegister.getText().toString().trim();
                String UserEmail = edtUserEmailRegister.getText().toString().trim();
                String UserPassWord = edtUserPassWordRegister.getText().toString().trim();
                CheckEditTextIsEmptyOrNot();
                if (CheckEditText){
                    UserRegisterFunction(url);
                } else {
                    Toast.makeText(SiginupActivity.this, "Bạn vui lòng nhập đầu đủ các trường", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void CheckEditTextIsEmptyOrNot(){
        String UserName = edtUserNameRegister.getText().toString();
        String UserEmail = edtUserEmailRegister.getText().toString();
        String UserPassWord = edtUserPassWordRegister.getText().toString();
        if (TextUtils.isEmpty(UserName) || TextUtils.isEmpty(UserPassWord) || TextUtils.isEmpty(UserPassWord)){
            CheckEditText = false;
        } else {
            CheckEditText = true;
        }
    }

    public void UserRegisterFunction(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(SiginupActivity.this, "Bạn đã thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SiginupActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SiginupActivity.this, "Lỗi thêm", Toast.LENGTH_SHORT).show();
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
                params.put("user_name", edtUserNameRegister.getText().toString().trim() );
                params.put("user_email", edtUserNameRegister.getText().toString().trim() );
                params.put("user_password", edtUserPassWordRegister.getText().toString().trim() );
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
