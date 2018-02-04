package com.htnsoft.unitech;

import java.io.Serializable;

/**
 * Created by TRUNG VAN on 2/3/2018.
 */

public class Courses implements Serializable {
    private int Id;
    private  String NameCourse;
    private String DetailCourse;

    public Courses(int id, String nameCourse, String detailCourse) {
        Id = id;
        NameCourse = nameCourse;
        DetailCourse = detailCourse;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNameCourse() {
        return NameCourse;
    }

    public void setNameCourse(String nameCourse) {
        NameCourse = nameCourse;
    }

    public String getDetailCourse() {
        return DetailCourse;
    }

    public void setDetailCourse(String detailCourse) {
        DetailCourse = detailCourse;
    }
}
