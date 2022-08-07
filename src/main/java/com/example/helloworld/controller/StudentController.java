package com.example.helloworld.controller;

import com.example.helloworld.model.Students;
import com.example.helloworld.repository.StudentRepositoryImplWithJDBC;
import com.example.helloworld.repository.StudentsRepoImplJPQL;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;


@RestController
public class StudentController {
    StudentRepositoryImplWithJDBC jdbc = new StudentRepositoryImplWithJDBC();
    private final StudentsRepoImplJPQL jpql;

    public StudentController(StudentsRepoImplJPQL jpql) {
        this.jpql = jpql;
    }

    @GetMapping("/allstudents")
    public List<Students> getStudents() throws SQLException {
        return jdbc.findAll();
    }
    @GetMapping("/students")
    public List<Students> getAllStudents() throws SQLException{
        return jpql.findAll();
    }

}
