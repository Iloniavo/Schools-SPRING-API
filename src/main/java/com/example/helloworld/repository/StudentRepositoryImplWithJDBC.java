package com.example.helloworld.repository;

import com.example.helloworld.model.Groups;
import com.example.helloworld.model.Students;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepositoryImplWithJDBC implements StudentRepository{

    public Connection connection() {
        try{
            Connection con  = DriverManager.getConnection("jdbc:postgresql://localhost:5432/schools", "postgres","iloreus");
            System.out.println("Connected");
            return con;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public List<Students> findAll() throws SQLException {
        Statement stmt = connection().createStatement();
        ResultSet resultSet;
        List<Students> students = new ArrayList<>();
        try {
            resultSet = stmt.executeQuery("" +
                    "SELECT students.id,students.name , students.groups , creation_date , groups.name AS name_groups " +
                    " FROM students " +
                    "INNER JOIN Groups " +
                    "ON groups.groups = students.groups" );

            while (resultSet.next()) {
                //Students
                Long id = resultSet.getLong("groups");
                String name = resultSet.getString("name");

                //Groups
                int groups_id = resultSet.getInt("groups");
                String groups_name = resultSet.getString("name");
                Date creationDate = resultSet.getDate("creation_date");
                students.add(new Students(id,name,new Groups(groups_id,groups_name, creationDate.toLocalDate())));
            }
            return students;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Request failed");
        }
        return null;
    }

    @Override
    public Students findById(Long id) throws SQLException {
        Statement stmt = connection().createStatement();
        ResultSet resultSet;
        Students student = null;
        try {
            resultSet = stmt.executeQuery("" +
                    "SELECT students.id,students.name , students.groups , creation_date , groups.name AS name_groups " +
                    " FROM students " +
                    "INNER JOIN Groups " +
                    "ON groups.groups = students.groups WHERE students.id = "+id );

            while (resultSet.next()) {
                //Students
                Long id_stu = resultSet.getLong("groups");
                String name = resultSet.getString("name");

                //Groups
                int groups_id = resultSet.getInt("groups");
                String groups_name = resultSet.getString("name");
                Date creationDate = resultSet.getDate("creation_date");
                student = new Students(id_stu,name,new Groups(groups_id,groups_name, creationDate.toLocalDate()));
            }
            return student;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Request failed");
            return null;
        }

    }

    @Override
    public Students save(Students s) throws SQLException {
        Statement stmt = connection().createStatement();
        ResultSet resultSet;
        Students student = null;
        try {
            resultSet = stmt.executeQuery("INSERT INTO Students VALUES (" + s.getName() + "," + s.getId() + "," + s.getGroups().getGroups() + ");");
            while (resultSet.next()) {
                //Students
                Long id_stu = resultSet.getLong("groups");
                String name = resultSet.getString("name");

                //Groups
                int groups_id = resultSet.getInt("groups");
                String groups_name = resultSet.getString("name");
                Date creationDate = resultSet.getDate("creation_date");
                student = new Students(id_stu, name, new Groups(groups_id, groups_name, creationDate.toLocalDate()));
            } return student;
        } catch (SQLException e) {
            e.printStackTrace();
                return null;
        } 
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        Statement stmt = connection().createStatement();
        try{
            stmt.executeUpdate("DELETE FROM students WHERE students.id="+id+";");
        } catch (SQLException e){
         e.printStackTrace();
        }
    }

    @Override
    public Students updateNameById(Long id, String newName) throws SQLException {
        Statement stmt = connection().createStatement();
        ResultSet resultSet;
        try{
            stmt.executeUpdate("UPDATE students set name = "+newName+" WHERE id = "+id);
           return findById(id);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Students> findWhereNameLike(String query) throws SQLException {
        Statement stmt = connection().createStatement();
        ResultSet resultSet;
        List<Students> students = new ArrayList<>();
        try{
            resultSet = stmt.executeQuery("" +
                    "SELECT students.id,students.name , students.groups , creation_date , groups.name AS name_groups " +
                    " FROM students " +
                    "INNER JOIN Groups " +
                    "ON groups.groups_id = students.groups" );

            while (resultSet.next()) {
                //Students
                Long id = resultSet.getLong("groups");
                String name = resultSet.getString("name");

                //Groups
                int groups_id = resultSet.getInt("groups");
                String groups_name = resultSet.getString("name");
                Date creationDate = resultSet.getDate("creation_date");
                students.add(new Students(id,name,new Groups(groups_id,groups_name, creationDate.toLocalDate())));
            }
            return students;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}

