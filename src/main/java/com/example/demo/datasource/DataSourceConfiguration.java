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
	
	@Bean(name = "primaryDataSource")
	@Primary  //配置该数据源为主数据源
	@Qualifier("primaryDataSource")
	@ConfigurationProperties(prefix = "datasource.primary")
	public DataSource primaryDataSource() {
		System.out.println("-------------------- primaryDataSource初始化 ---------------------");
		return DataSourceBuilder.create().build();
	}

	
	@Bean(name = "secondDataSource")
	@Qualifier("secondDataSource")
	@ConfigurationProperties(prefix = "datasource.second")
	public DataSource secondDataSource() {
		System.out.println("-------------------- secondDataSource初始化---------------------");
		return DataSourceBuilder.create().build();
	}
	

}
