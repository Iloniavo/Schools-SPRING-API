package com.example.helloworld.repository;

import com.example.helloworld.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentsRepoImplJPQL extends  JpaRepository<Students, Long> {

    @Query(value = "SELECT u from Students u")
    List<Students> findAll();


    @Query(value = "SELECT u FROM Students u WHERE u.id =:id ")
    Optional<Students> findById(@Param("id") int id) ;

    void deleteById(Long id);

    List<Students>findStudentsByNameLike(String name);
}
