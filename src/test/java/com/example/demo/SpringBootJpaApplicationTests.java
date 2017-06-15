package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJpaApplicationTests {
	
	@Autowired
	private UserDao userDao;

	@Test
	public void insertUserList() {
		
		//先添加用户数据
		for(int i=1;i<=2;i++){
			User user = new User();
			user.setUserName("AA"+i);
			user.setAge(20+i);
			userDao.save(user);
		}
		
		User user = userDao.findByUserName("1");
		System.out.println(user.toString());
		
	}
	
	
	@Test
	public void findUser(){
		
//		User user = userDao.findByUserName("AA1");
		
		User user = userDao.findByUserNameAndAge("AA2",22);
		System.out.println(user.toString());
		
		
	}

}
