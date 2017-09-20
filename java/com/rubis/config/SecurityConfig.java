package com.rubis.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcAccessor;

import com.rubis.bean.CustomSuccessHandler;

@Configuration 
@EnableTransactionManagement
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	 CustomSuccessHandler customSuccessHandler;
	
	@Autowired
	DataSource dataSource;
//	@Autowired
//	JdbcOperations tpl;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		((JdbcTemplate) tpl)
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select email,password, enabled from users where email=?;")
		.authoritiesByUsernameQuery("select u.email,a.authoritie FROM users u, user_authoritie a WHERE u.user_id=a.id_user and u.email=?;");
//				+ "select u.email,a.authorities FROM users u, user_authorities ua, authorities a WHERE u.user_id=ua.user_id and ua.authoritie=a.authoritie_id and u.email=?;");
 
		
	}	
	

     
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

 
		http.authorizeRequests().antMatchers("/admin/**")
		.access("hasRole('ROLE_USER')").and().authorizeRequests().antMatchers("/bureau/**")
		.access("hasRole('ROLE_USER')").and().formLogin()
		.loginPage("/login").failureUrl("/login?error").successHandler(customSuccessHandler)
		.usernameParameter("email")
		.passwordParameter("password")
			.and().logout().logoutSuccessUrl("/login?logout").and()
			.logout().logoutSuccessUrl("/login?logout")
		.and()
			.exceptionHandling().accessDeniedPage("/403") 
			.and().csrf().and().headers().frameOptions().disable();
		
	}
}