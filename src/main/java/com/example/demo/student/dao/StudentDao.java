package com.example.demo.student.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.student.entity.Student;

public interface StudentDao extends JpaRepository<Student, Long> {

}
