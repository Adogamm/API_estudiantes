package com.example.springboot.app.service;

import java.util.List;

import com.example.springboot.app.model.Student;

public interface StudentService {

    Student createStudent(Student Student);

    Student updateStudent(Student Student);

    List<Student> getAllStudent();

    Student getStudentById(long StudentId);

    void deleteStudent(long id);

}
