package com.example.helloworld.repository;

import com.example.helloworld.model.Students;

import java.sql.SQLException;
import java.util.List;

public interface StudentRepository {
      List<Students> findAll() throws SQLException;

     Students findById(Long id) throws SQLException;
     Students save(Students s) throws SQLException;

     void deleteById(Long id) throws SQLException;

     Students updateNameById(Long id, String newName) throws SQLException;

     List<Students> findWhereNameLike(String query) throws SQLException;
     //select * from students where name like '%query%'

    //Implémenter cette interface avec une classe concrète avec JDBC
    //proposez une autre implémentation avec JPQL (Java Persistence Query Language (vous pouvez voir l'annotation : @Query)
     //hei-ryan
}
