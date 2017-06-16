#spring boot jpa 多数据源实例学习
1.创建两个数据库,名称可以自行定义，这里我定义为:teacher 、 student 两个数据库。
2.分别在以上两个数据库中创建数据表teacher 、 student。
  1）表创建语句。
	  CREATE TABLE `teacher` (
	  `id` bigint(10) NOT NULL AUTO_INCREMENT,
	  `teacher_name` varchar(50) DEFAULT NULL COMMENT '教师名称',
	  `job_no` varchar(20) DEFAULT NULL COMMENT '工号',
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;

	CREATE TABLE `student` (
	  `id` bigint(10) NOT NULL AUTO_INCREMENT,
	  `student_name` varchar(50) DEFAULT NULL COMMENT '学生名称',
	  `student_no` varchar(20) DEFAULT NULL COMMENT '学号',
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
3.数据库配置，请参考 application.yml 文件。
4.自定义一个数据源配置类  DataSourceConfiguration.java
  1).数据源配置类中定义两个数据源：teacherDataSource 和 studentDataSource，
  	  其中teacherDataSource 通过@Primary标记该数据源为主数据源。
  	  
5.详细实现每个数据源，参见 TeacherDataSourceConfigurer.java 和 StudentDataSourceConfigurer.java
  1).注意实体类和Repository所在的包名。建议包名写的具体完善些。
  	如，实体类，com.example.demo.teacher.entity
  	如，Dao层接口，com.example.demo.teacher.dao。
  	这样写，可以让程序具体的定位到某个具体的数据源。
  	
  	如果只写了上级某些目录，如实体类和Dao层接口都写为 com.example.demo,
  	系统不能很明确的定位到具体的数据源，它会从主数据源开始查找。
  	如，我在测试类中查找学生列表时，仔细查看控制台，会输出  Table not found: teacher。
6.编写测试类。  	
  	