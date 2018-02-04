package com.htnsoft.unitech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView listviewCourse;
    ArrayList<Courses> arrayCourses;
    CoursesAdapter adapter;
    String urlDetete ="http://chuan03.com/unitech/delete.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ánh xạ listview

        listviewCourse = (ListView) findViewById(R.id.listviewCourse) ;
        //Khai báo mảng khóa học
        arrayCourses = new ArrayList<>();

        //Khởi tạo adapter
        adapter = new CoursesAdapter(this, R.layout.row_course, arrayCourses);
        //Gán list view vào adapter
        listviewCourse.setAdapter(adapter);

        ReadJSON("http://chuan03.com/unitech/");
    }

    //Hàm đọc dữ liệu từ file JSON
    private void ReadJSON (String url){
        final RequestQueue  requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arrayCourses.clear();
                        for (int i =0; i<response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrayCourses.add(new Courses(
                                        object.getInt("Id"),
                                        object.getString("NameCourse"),
                                        object.getString("DetailCourse")
                                ));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }


    //Xóa dữ lieu từ Database

    public void DeleteCourse(final int id){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDetete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("Success")){
                    Toast.makeText(MainActivity.this, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                    ReadJSON("http://chuan03.com/unitech/");
                } else{
                    Toast.makeText(MainActivity.this, "Lỗi xóa", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idCourse", String.valueOf(id));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    //Thêm dữ liệu vào database

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addcourse, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addCourse){
            startActivity(new Intent(MainActivity.this, AddCourseActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
