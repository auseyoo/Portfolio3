package com.namyang.nyorder.config.db;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.namyang.nyorder.config.MyRoutingDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableTransactionManagement
@MapperScan({"com.namyang.nyorder.*.dao"})
//@PropertySource(value = "classpath:config/database.yml", factory = YamlPropertySourceFactory.class, ignoreResourceNotFound = true)
public class DatabaseConfig {
	
	
	@Autowired
    private ApplicationContext applicationContext;
	
	@Value("${dev.driverClassName}")
	private String devDriverClassName;
	
	@Value(value = "${dev.jdbcUrl}")
	private String devJdbcUrl;
	
	@Value(value = "${dev.username}")
	private String devUsername;
	
	@Value(value = "${dev.password}")
	private String devPassword;
	
	@Value(value = "${real.driverClassName}")
	private String realDriverClassName;
	
	@Value(value = "${real.jdbcUrl}")
	private String realJdbcUrl;
	
	@Value(value = "${real.username}")
	private String realUsername;
	
	@Value(value = "${real.password}")
	private String realPassword;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
		yaml.setResources(new ClassPathResource("config/database.yml"));
		propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
		return propertySourcesPlaceholderConfigurer;
	}
	
	@Bean 
	public DataSource createRouterDatasource() { 
		AbstractRoutingDataSource routingDataSource = new MyRoutingDataSource(); 
		
		Map<Object, Object> targetDataSources = new HashMap<>(); 
			
		
		log.debug("createRouterDatasource   devJdbcUrl::" + devJdbcUrl + ", :: " + devDriverClassName + ", ::" );
		
		targetDataSources.put("dev", 
				createDataSource(devJdbcUrl, devUsername, devPassword)); 
		targetDataSources.put("real", 
				createDataSource(realJdbcUrl, realUsername, realPassword));
		
		routingDataSource.setTargetDataSources(targetDataSources); 
		
		return routingDataSource; 
	} 
	
	private DataSource createDataSource(String url, String user, String password) { 
		com.zaxxer.hikari.HikariDataSource dataSource = new com.zaxxer.hikari.HikariDataSource(); 
				
		dataSource.setDriverClassName(devDriverClassName); 
		dataSource.setUsername(user); 
		dataSource.setPassword(password); 
		dataSource.setJdbcUrl(url); 
		
		return dataSource; 
	}
	
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean  = new SqlSessionFactoryBean();
        
        sessionFactoryBean.setDataSource(dataSource);
                
        //sessionFactoryBean.setTypeAliasesPackage("com.stunstun.spring.repository.entity");
        sessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:config/mybatis-config.xml"));
        sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mappers/**/*Mapper.xml"));
                
        return sessionFactoryBean.getObject();
    }
    
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        //return new SqlSessionTemplate(sqlSessionFactory);
        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.SIMPLE);
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
    	
    	DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(createRouterDatasource());
    	transactionManager.setGlobalRollbackOnParticipationFailure(false);    	
    	return transactionManager;
    }


}
