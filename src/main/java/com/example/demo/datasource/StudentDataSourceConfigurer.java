package com.example.demo.datasource;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef="entityManagerFactoryStudent",
						transactionManagerRef="transactionManagerStudent",
						basePackages={"com.example.demo.student.dao"})
public class StudentDataSourceConfigurer {
	
	//http://www.jianshu.com/p/9f812e651319
	/**
	 * 注入 jpa 配置实体
	 */
	@Autowired
	private JpaProperties jpaProperties;
	
	/**
	 * 注入数据源
	 */
	@Autowired
	@Qualifier("studentDataSource")
	private DataSource studentDataSource;
	
	/**
	 * 配置 EntityManager 实体类
	 * @param builder
	 * @return
	 */
	@Bean(name = "entityManagerStudent")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryStudent(builder).getObject().createEntityManager();
    }
	
	/**
	 * 配置 EntityManager 工厂实体
	 * @param builder
	 * @return
	 */
	@Bean(name = "entityManagerFactoryStudent")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryStudent(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(studentDataSource)
                .properties(getVendorProperties(studentDataSource))
                .packages("com.example.demo.student.entity") //设置实体类所在位置
                .persistenceUnit("studentPersistenceUnit")
                .build();
    }

	/**
	 * 配置TransactionManager
	 */
	@Bean(name = "transactionManagerStudent")
    public PlatformTransactionManager transactionManagerStudent(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryStudent(builder).getObject());
    }
	
	/**
	 * 获取 jpa 配置信息
	 * @param dataSource
	 * @return
	 */
	private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

}
