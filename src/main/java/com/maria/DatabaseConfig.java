package com.maria;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.maria.properties.OptDatabaseProperties;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@EnableConfigurationProperties(value = OptDatabaseProperties.class) 
 public abstract class DatabaseConfig { 
 	 
 	@Autowired 
 	private OptDatabaseProperties optDatabaseProperties; 
 
 
     @Bean 
     public abstract DataSource dataSource(); 
 
 
     protected void configureDataSource(org.apache.tomcat.jdbc.pool.DataSource dataSource) {
    	log.info("configureDataSource");
    	log.info("drivername : "+optDatabaseProperties.getDriverClassName());
     	dataSource.setDriverClassName(optDatabaseProperties.getDriverClassName()); 
     	dataSource.setUrl(optDatabaseProperties.getUrl()); 
     	dataSource.setUsername(optDatabaseProperties.getUserName()); 
     	dataSource.setPassword(optDatabaseProperties.getPassword()); 
        dataSource.setMaxActive(optDatabaseProperties.getMaxActive()); 
        dataSource.setMaxIdle(optDatabaseProperties.getMaxIdle()); 
        dataSource.setMinIdle(optDatabaseProperties.getMinIdle()); 
        dataSource.setMaxWait(optDatabaseProperties.getMaxWait()); 
        dataSource.setTestOnBorrow(false); 
        dataSource.setTestOnReturn(false); 
     } 
 } 



/**
 * @author 상용
 * DataSource 및 SqlSessionFactoryBean 등록
 * 
 */
@Slf4j
@Configuration
@Lazy
@EnableTransactionManagement
@MapperScan(basePackages = {"com.maria.**.repository"}, sqlSessionFactoryRef="optSqlSessionFactory")
class DefaultDatabaseConfig extends DatabaseConfig {
	
	@Autowired
	private ApplicationContext applicationContext;

	@Bean(destroyMethod="close")
	public DataSource dataSource(){
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
		configureDataSource(dataSource);
		return dataSource;		
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(){
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource());
		transactionManager.setGlobalRollbackOnParticipationFailure(false);
		return transactionManager;
	}
	
	@Bean(name="optSqlSessionFactory")
	public SqlSessionFactory optSqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		log.info("sessionFactoryBean create");
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setTypeAliasesPackage("com.maria.**.domain");
		sessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:META-INF/mybatis/mybatis-config.xml"));
		sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:META-INF/mybatis/mapper/*.xml"));
		return sessionFactoryBean.getObject();
		
		
	}
}
