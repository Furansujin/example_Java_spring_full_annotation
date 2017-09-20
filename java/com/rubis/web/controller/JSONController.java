package com.rubis.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rubis.model.Employee;
 

@Controller
@RequestMapping(value ="/bureau/jsonn", produces = "application/json")
public class JSONController {
	
	@RequestMapping(value="{name}", method = RequestMethod.GET)
	public @ResponseBody Employee getEmployeeInJSON(@PathVariable String name) {

		Employee employee = new Employee();
		employee.setName(name);
		employee.setEmail( "emiltest");
		
		return employee;

	}
	
}