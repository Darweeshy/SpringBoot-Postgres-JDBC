package org.example.springjdbcex.repo;

import org.example.springjdbcex.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepo
{
    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Student s)
    {
        String sql = "insert into student(rollno , name , marks) values(?,?,?)";
        int rows = jdbc.update(sql, s.getRollNo() ,s.getName(), s.getMarks());
        System.out.println(rows);
    }

    public List<Student> findAll()
    {
        List<Student> students = new ArrayList<>();
        String sql = "select * from student";
        return jdbc.query(sql , ( rs,  rowNum) ->
        {
            Student student = new Student();
            student.setRollNo(rs.getInt("rollno"));
            student.setName(rs.getString("name"));
            student.setMarks(rs.getInt("marks"));
            return student;

        });

    }
}
