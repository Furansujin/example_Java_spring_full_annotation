package com.rubis.web.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rubis.bean.SessionFactoryRubis;
import com.rubis.config.AppConfig;
import com.rubis.config.DataBaseConfig;
import com.rubis.dao.DaoUserRubis;
import com.rubis.model.UserRubis;

@Controller 
@ComponentScan({ "com.rubis.*" })
public class MainController {

	@Autowired
    private SessionFactoryRubis sessionFactoryRubis;
	
	
	@RequestMapping(method=RequestMethod.GET, value = "/bureau/co"  ) 
    public @ResponseBody  UserRubis getPersonne() {  
		 UserRubis a = sessionFactoryRubis.getUserrubis();
			       
		return a;
	}
	 
	@RequestMapping(value = "/bureau**", method = RequestMethod.GET)
	public ModelAndView adminPage() {
		ModelAndView model = new ModelAndView();

		model.addObject("title", "Spring Security + Hibernate Example");
		model.addObject("nom", sessionFactoryRubis.getNom());
		model.setViewName("bureau");
		
		return model;

	}
	
	@RequestMapping(value = "/bureau/chat", method = RequestMethod.GET)
	public ModelAndView chatPage() {
		ModelAndView model = new ModelAndView();
		
		model.addObject("title", "Spring Security + Hibernate Example");
		model.addObject("nom", sessionFactoryRubis.getNom());
		model.setViewName("chat");

		return model;

	}
	
	
	
//	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
//	public ModelAndView adminPage() {
//
//		ModelAndView model = new ModelAndView();
//		model.addObject("title", "Spring Security Login Form - Database Authentication");
//		model.addObject("message", sessionFactoryRubis.getNom()); 
//		model.setViewName("admin");
//
//		return model;
//
//	}

	@RequestMapping(value ={ "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("index");

		return model;

	}
	
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();
		 
		//check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);
		
			model.addObject("username", userDetail.getUsername());
			
		}
		
		model.setViewName("403");
		return model;

	}

}