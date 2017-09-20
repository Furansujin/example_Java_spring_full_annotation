package com.rubis.config.core;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException; 
import org.springframework.web.context.request.RequestContextListener; 
import org.springframework.web.filter.HiddenHttpMethodFilter; 
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.rubis.config.AppConfig;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() { 
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	 @Override
	    protected Filter[] getServletFilters() {
	        return new Filter[] { new HiddenHttpMethodFilter() };
	    }

	    @Override
	    public void onStartup(ServletContext servletContext) throws ServletException {
	        super.onStartup(servletContext);
	        servletContext.addListener(new RequestContextListener());
	    } 
 
	
}