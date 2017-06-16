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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef="entityManagerFactoryTeacher",
						transactionManagerRef="transactionManagerTeacher",
						basePackages={"com.example.demo.teacher.dao"}) //设置Repository所在位置
public class TeacherDataSourceConfigurer {
	
	/**
	 * 注入 jpa 配置实体
	 */
	@Autowired
	private JpaProperties jpaProperties;
	
	/**
	 * 注入数据源
	 */
	@Autowired
	@Qualifier("teacherDataSource")
	private DataSource teacherDataSource;
	
	
	/**
	 * 配置 EntityManager 实体类
	 * @param builder
	 * @return
	 */
	@Bean(name = "entityManagerTeacher")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryTeacher(builder).getObject().createEntityManager();
    }
	
	/**
	 * 配置 EntityManager 工厂实体
	 * @param builder
	 * @return
	 */
	@Bean(name = "entityManagerFactoryTeacher")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryTeacher(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(teacherDataSource)
                .properties(getVendorProperties(teacherDataSource))
                .packages("com.example.demo.teacher.entity") //设置实体类所在位置
                .persistenceUnit("teacherPersistenceUnit")
                .build();
    }

	/**
	 * 配置TransactionManager
	 */
	@Bean(name = "transactionManagerTeacher")
    @Primary
    public PlatformTransactionManager transactionManagerTeacher(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryTeacher(builder).getObject());
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
