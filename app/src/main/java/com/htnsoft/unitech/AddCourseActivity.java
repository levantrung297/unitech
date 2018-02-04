package com.htnsoft.unitech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class AddCourseActivity extends AppCompatActivity {

    Button btnSaveCourse, btnCancelCourse;
    EditText edtNameCourse, edtDetailCourse;
    String urlInsert = "http://chuan03.com/unitech/insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        AnhXa();
        btnSaveCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namecourse = edtNameCourse.getText().toString().trim();
                String detailcourse = edtDetailCourse.getText().toString().trim();

                if ( namecourse.isEmpty() || detailcourse.isEmpty()){
                    Toast.makeText(AddCourseActivity.this, "Vui lòng nhập vào dữ liệu", Toast.LENGTH_SHORT).show();
                } else {
                    AddCourse(urlInsert);
                }
            }
        });

        btnCancelCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AddCourse(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Success")){
                    Toast.makeText(AddCourseActivity.this,"Thêm Thành công",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddCourseActivity.this, MainActivity.class));

                } else{
                    Toast.makeText(AddCourseActivity.this,"Lỗi thêm", Toast.LENGTH_SHORT).show();
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
                params.put("NameCourse", edtNameCourse.getText().toString().trim());
                params.put("DetailCourse", edtDetailCourse.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa(){
        btnSaveCourse = (Button) findViewById(R.id.btnSaveCourse);
        btnCancelCourse = (Button) findViewById(R.id.btnCancelCourse);
        edtNameCourse = (EditText) findViewById(R.id.edtNameCourse);
        edtDetailCourse = (EditText) findViewById(R.id.edtDetailCourse);

    }
}
