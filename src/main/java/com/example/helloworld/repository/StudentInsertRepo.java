package com.example.helloworld.repository;

import com.example.helloworld.model.Students;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentInsertRepo {
    @PersistenceContext
    private EntityManager entityManager;
    public void insertWithQuery(Students student) {
        entityManager.createNativeQuery("INSERT INTO person (id, first_name, last_name) VALUES (?,?,?)")
                .setParameter(1, student.getId())
                .setParameter(2, student.getName())
                .setParameter(3, student.getGroups())
                .executeUpdate();
    }
}
