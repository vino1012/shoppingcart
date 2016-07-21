package com.niit.shoppingcart.config;

import java.util.Locale.Category;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.CategoryDAOimpl;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.ProductDAOimpl;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.dao.SupplierDAOimpl;
import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.dao.UserDAOimpl;
import com.niit.shoppingcart.model.Product;
import com.niit.shoppingcart.model.Supplier;
import com.niit.shoppingcart.model.User;

@Configuration
@ComponentScan("com.niit.shoppingcart")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	@Bean(name="dataSource")
	public DataSource getDataSource(){
	DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:tmp.db;INIT=CREATE SCHEMA IF NOT EXIST NIITDB");
		
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");
	
		return dataSource;
				
	}
	private Properties getHibernateProperties(){
		Properties properties=new Properties();
		properties.put("hibernate.show_sql","true");
		properties.put("hibernate_dialect","org.hibernate.dialect.H2dialect");
		return properties;
		
	}
	
	@Autowired
	@Bean(name="SessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder sessionBuilder=new LocalSessionFactoryBuilder(dataSource);
		Properties getHibernateProperties = null;
		sessionBuilder.addProperties(getHibernateProperties);
		sessionBuilder.addAnnotatedClass(Category.class);
		sessionBuilder.addAnnotatedClass(Supplier.class);
		sessionBuilder.addAnnotatedClass(Product.class);
		sessionBuilder.addAnnotatedClass(User.class);

		return sessionBuilder.buildSessionFactory();
		
	}
	
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
	HibernateTransactionManager transactionManager=new HibernateTransactionManager(sessionFactory);
	return transactionManager;

	}
	
	@Autowired
	@Bean(name="categoryDAO")
	public CategoryDAO getcategoryDAO(SessionFactory sessionFactory){
		return new CategoryDAOimpl(sessionFactory);
	}
	
	@Autowired
	@Bean(name="supplierDAO")
	public SupplierDAO getsupplierDAO(SessionFactory sessionFactory){
		return new SupplierDAOimpl(sessionFactory);
	}
	@Autowired
	@Bean(name="productDAO")
	public ProductDAO getproductDAO(SessionFactory sessionFactory){
		return new ProductDAOimpl(sessionFactory);
	}
	@Autowired
	@Bean(name="userDAO")
	public UserDAO getuserDAO(SessionFactory sessionFactory){
		return new UserDAOimpl(sessionFactory);
	}
	
}