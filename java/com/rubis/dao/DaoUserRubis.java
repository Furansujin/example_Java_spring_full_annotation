package com.rubis.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.rubis.bean.SessionFactoryRubis;
import com.rubis.model.UserRubis;

@Component
public class DaoUserRubis {
	
//	@Autowired
//	JdbcOperations tpl;
	
	@Autowired
    private SessionFactoryRubis sessionFactoryRubis;
	
 

	public UserRubis userRubisByEmail(String email){
		UserRubis user = new UserRubis();
		ResultSet rs=null;
		user.setEmail(email);
		     try {
				Connection con= sessionFactoryRubis.getDataSource().getConnection();
				java.sql.PreparedStatement stmt = con.prepareStatement("SELECT * FROM `users` WHERE `email`='"+email+"'");
				 
				rs= stmt.executeQuery();
				if(rs.next()){ 
					user.setName(rs.getString("nom")); 
					user.setIdUser(rs.getInt("user_id")); 
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return user;
	}
	
	
	
	

}
