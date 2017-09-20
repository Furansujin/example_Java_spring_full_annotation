package com.rubis.bean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication; 
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mysql.jdbc.PreparedStatement;
import com.rubis.dao.DaoUserRubis; 

@Component  
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
//	@Autowired
//	JdbcOperations tpl;
	
	@Autowired
    private SessionFactoryRubis sessionFactoryRubis;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	DaoUserRubis daoUserRubis;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	            throws IOException {
		
		 
		
//		DataSource dt = ((JdbcTemplate) tpl).getDataSource();
		sessionFactoryRubis.setDataSource(dataSource); 
	 
		sessionFactoryRubis.setUserrubis(daoUserRubis.userRubisByEmail(authentication.getName()));
	    redirectStrategy.sendRedirect(request, response, "/bureau");
	        
	        
	    }
	
	
	  public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
	        this.redirectStrategy = redirectStrategy;
	    }
	 
	  
	    protected RedirectStrategy getRedirectStrategy() {
	        return redirectStrategy;
	    }
	 
	
	

}
