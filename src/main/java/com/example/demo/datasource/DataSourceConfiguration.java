package com.example.demo.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfiguration { 
	
	@Bean(name = "teacherDataSource")
	@Primary  //配置该数据源为主数据源
	@Qualifier("teacherDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.teacher")
	public DataSource teacherDataSource() {
		System.out.println("-------------------- primaryDataSource初始化 ---------------------");
		return DataSourceBuilder.create().build();
	}

	
	@Bean(name = "studentDataSource")
	@Qualifier("studentDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.student")
	public DataSource studentDataSource() {
		System.out.println("-------------------- studentDataSource初始化---------------------");
		return DataSourceBuilder.create().build();
	}
	

}
