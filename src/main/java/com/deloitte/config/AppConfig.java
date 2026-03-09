package com.deloitte.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration //it tells spring that this this file/ class is a configuration file
 @EnableWebMvc
@ComponentScan(basePackages="com.deloitte")
public class AppConfig  {

	
	
	@Bean
	public ViewResolver viewResolver() {  //view resolver will helps to view the jsp files
		System.out.println("=== Creating ViewResolver ===");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");  //WBB-INF/views/ jsp file
		resolver.setSuffix(".jsp");
		return resolver;
	}
	

	@Bean 
	DataSource dataSource() {  //---datasource it is a obj 
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/flight_reservation_system");
		dataSource.setUsername("root");
		dataSource.setPassword("Agal@2003");  
		return dataSource;
	}
	
	@Bean
	JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
}
