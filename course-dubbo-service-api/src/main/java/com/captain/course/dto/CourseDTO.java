package com.captain.course.dto;

import com.captain.thrift.user.dto.TeacherDTO;

import java.io.Serializable;

/**
 * @ClassName CourseDTO
 * @Description TODO
 * @Author mengpeng
 * @Date 2020/5/5 20:26
 * @Version 1.0
 */
public class CourseDTO implements Serializable {


    private int id;

    private String title;

    private String description;

    private TeacherDTO teacher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }
}
