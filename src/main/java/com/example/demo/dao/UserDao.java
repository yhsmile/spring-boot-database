package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.User;

public interface UserDao extends JpaRepository<User, Long> {
	//根据用户名查询
	User findByUserName(String userName);
	
	//根据用户名和年龄查询
	@Query("from User u where u.userName=?1 and u.age=?2")
	User findByUserNameAndAge(String userName,Integer age);
	
	
	
	
	//@Query 注解的使用非常简单，只需在声明的方法上面标注该注解，同时提供一个 JP QL 查询语句即可
	@Query("from User u where u.userName=:userName")
	User findUser(@Param("userName") String userName);
	
	//@Query 注解 第二种使用方法
//	@Query("from User u where u.userName=?1")
//	User findUser(String userName);
	
}
