package com.rubis.bean;

import javax.sql.DataSource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.rubis.model.UserRubis;

@Component 
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionFactoryRubis{
	
	
	private UserRubis userrubis = new UserRubis();
	private DataSource dataSource;
//	private JdbcOperations tpl;
	
		public String getNom() {
			return userrubis.getName();
		}
		
		public void setNom(String nom) {
			this.userrubis.setName(nom);
		}

		public UserRubis getUserrubis() {
			return userrubis;
		}

		public void setUserrubis(UserRubis userrubis) {
			this.userrubis = userrubis;
		}

		public DataSource getDataSource() {
			return dataSource;
		}

		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}

//		public JdbcOperations getTpl() {
//			return tpl;
//		}
//
//		public void setTpl(JdbcOperations tpl) {
//			this.tpl = tpl;
//		}
		
		 

}
