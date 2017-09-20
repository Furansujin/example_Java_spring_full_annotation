package com.rubis.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
 

@EnableWebMvc
@Configuration
@ComponentScan({ "com.rubis.*" })
@EnableTransactionManagement
@Import({ SecurityConfig.class})
public class AppConfig
{
	 
 
	
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/rubis");
	    driverManagerDataSource.setUsername("root"); 
	    return driverManagerDataSource;
	}
//	
//	@Bean(destroyMethod = "close")
//    public javax.sql.DataSource datasource() {
//        org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
//        ds.setDriverClassName("com.mysql.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://localhost:3306/rubis");
//        ds.setUsername("root");
//        ds.setPassword("");
//        ds.setInitialSize(5);
//        ds.setMaxActive(10);
//        ds.setMaxIdle(5);
//        ds.setMinIdle(2);
//        return ds;
//    }
// 
//    @Bean(name = "tpl")
//    public JdbcOperations tpl() {
//        return new JdbcTemplate(datasource());
//    }
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setExposeContextBeansAsAttributes(true);
		return viewResolver;
	}
	
	
	
}