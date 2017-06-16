package com.example.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.student.dao.StudentDao;
import com.example.demo.student.entity.Student;
import com.example.demo.teacher.dao.TeacherDao;
import com.example.demo.teacher.entity.Teacher;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJpaApplicationTests {
	
	@Autowired
	private TeacherDao teacherDao;
	
	@Autowired
	private StudentDao studentDao;

	@Test
	public void insertTeacher(){
		
		for(int i=1;i<=8;i++){
			Teacher teacher = new Teacher();
			teacher.setTeacherName("TName"+i);
			teacher.setJobNo("1000"+i);
			teacherDao.save(teacher);
		}

		
	}
	
	@Test
	public void insertStudent(){
		
		for(int i=1;i<=8;i++){
			Student st = new Student();
			st.setStudentName("SName"+i);
			st.setStudentNo("s00"+i);
			studentDao.save(st);
		}
		
		
	}
	
	
	@Test
	public void findTeacher(){
		List<Teacher> list = teacherDao.findAll();
		for(Teacher t:list){
			System.out.println(t.toString());
		}
		
	}
	
	@Test
	public void findStudent(){
		List<Student> list = studentDao.findAll();
		for(Student s:list){
			System.out.println(s.toString());
		}
		
	}

}
