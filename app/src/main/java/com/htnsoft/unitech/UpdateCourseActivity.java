package com.htnsoft.unitech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class UpdateCourseActivity extends AppCompatActivity {
    EditText edtUpdateNameCourse, edtUpdateDetailCourse;
    Button btnUpdateCourse, btnUpdateCancel;
    int id = 0;
    String UpdateUrl = "http://chuan03.com/unitech/update.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);

        Intent intent = getIntent();
        Courses courses = (Courses) intent.getSerializableExtra("DataCourse");
        //Toast.makeText(this, courses.getNameCourse() , Toast.LENGTH_SHORT).show();

        AnhXa();
        id = courses.getId();
        edtUpdateNameCourse.setText(courses.getNameCourse());
        edtUpdateDetailCourse.setText(courses.getDetailCourse());


        //Bắt sự kiện cập nhật.
        btnUpdateCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NameCourse = edtUpdateNameCourse.getText().toString().trim();
                String DetailCourse = edtUpdateDetailCourse.getText().toString().trim();
                if (NameCourse.matches("") || DetailCourse.equals("")){
                    Toast.makeText(UpdateCourseActivity.this, "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    UpdateCourse(UpdateUrl);
                }

            }
        });

        //Bắt sự kiện hủy
        btnUpdateCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void UpdateCourse(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Success")){
                    Toast.makeText(UpdateCourseActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateCourseActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(UpdateCourseActivity.this, "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateCourseActivity.this, "Lỗi vui lòng cập nhật lại", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id", String.valueOf(id));
                params.put("NameCourse", edtUpdateNameCourse.getText().toString().trim());
                params.put("DetailCourse", edtUpdateDetailCourse.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void AnhXa(){
        edtUpdateNameCourse = (EditText) findViewById(R.id.edtUpdateNameCourse);
        edtUpdateDetailCourse = (EditText) findViewById(R.id.edtUpdateDetailCourse);
        btnUpdateCourse = (Button) findViewById(R.id.btnUpdateCourse);
        btnUpdateCancel = (Button) findViewById(R.id.btnUpdateCancel);

    }
}
