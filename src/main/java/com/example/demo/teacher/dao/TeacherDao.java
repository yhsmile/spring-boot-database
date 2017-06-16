/**
 * 
 */
package com.example.demo.teacher.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.teacher.entity.Teacher;

/**
 * @author Administrator
 *
 */
public interface TeacherDao extends JpaRepository<Teacher, Long> {

}
