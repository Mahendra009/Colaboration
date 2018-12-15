package com.niit.BlogBackEnd.Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.niit.BlogBackEnd")
@EnableTransactionManagement
public class OracleConfig {
	
	private final static String Database_Driver = "oracle.jdbc.OracleDriver";
	private final static String Database_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private final static String Database_UserName = "system";
	private final static String Database_Password = "9415016516";
	private final static String Database_Dialect = "org.hibernate.dialect.Oracle10gDialect";
	
	
	@Bean(name="dataSource")
	public DataSource getDataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(Database_Driver);
		dataSource.setUrl(Database_URL);
		dataSource.setUsername(Database_UserName);
		dataSource.setPassword(Database_Password);
		System.out.println("------------------------DataSource Created---------------------");
		return dataSource;
		
	}
	
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder lsfb = new LocalSessionFactoryBuilder(dataSource);
		lsfb.addProperties(getHibernateProperties());
		lsfb.scanPackages("com.niit.BlogBackEnd.model");
		System.out.println("------------------------SessionFactory Created---------------------");
		return lsfb.buildSessionFactory();
		
	}
	private Properties getHibernateProperties()
	{
		Properties hibernateprop = new Properties();
		hibernateprop.put("hibernate.dialect", Database_Dialect);
		hibernateprop.put("hibernate.hbm2ddl.auto", "update");
		hibernateprop.put("hibernate.show_sql", "true");
		hibernateprop.put("hibernate.format_sql", "true");
		System.out.println("------------------------Hibernate Properties Created---------------------");
		return hibernateprop;
	}
	@Bean
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory)
	{
		return new HibernateTransactionManager(sessionFactory);
	}


}
