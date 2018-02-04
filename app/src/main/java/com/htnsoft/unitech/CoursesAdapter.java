package com.htnsoft.unitech;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by TRUNG VAN on 2/3/2018.
 */

public class CoursesAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private ArrayList<Courses> courseslist;

    public CoursesAdapter(MainActivity context, int layout, ArrayList<Courses> courseslist) {
        this.context = context;
        this.layout = layout;
        this.courseslist = courseslist;
    }

    @Override
    public int getCount() {
        return courseslist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class  ViewHolder{
        TextView txtNameCourse, txtDetailCourse;
        ImageButton ibtDelete, ibtEdit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtNameCourse    = (TextView) convertView.findViewById(R.id.txtNameCourse);
            holder.txtDetailCourse  = (TextView) convertView.findViewById(R.id.txtDetailCourse);
            holder.ibtDelete        = (ImageButton) convertView.findViewById(R.id.ibtDelete);
            holder.ibtEdit          = (ImageButton) convertView.findViewById(R.id.ibtEdit);

            convertView.setTag(holder);
        } else{

            holder = (ViewHolder) convertView.getTag();
        }

        final Courses course = courseslist.get(position);

        holder.txtDetailCourse.setText(course.getDetailCourse());
        holder.txtNameCourse.setText(course.getNameCourse());

        // Bắt sự kiện xóa & sửa
        holder.ibtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Bạn đã nhấn xóa", Toast.LENGTH_SHORT).show();
                XacNhanXoa(course.getNameCourse(), course.getId());
            }
        });

        holder.ibtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, course.getNameCourse(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, UpdateCourseActivity.class);
                intent.putExtra("DataCourse", course);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    private void XacNhanXoa(String namcourse, final int id){
        AlertDialog.Builder diaglogDelete = new AlertDialog.Builder(context);
        diaglogDelete.setMessage("Bạn Có Muốn Xóa" + namcourse + "Không ?");
        diaglogDelete.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.DeleteCourse(id);
            }
        });
        diaglogDelete.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        diaglogDelete.show();
    }
}
