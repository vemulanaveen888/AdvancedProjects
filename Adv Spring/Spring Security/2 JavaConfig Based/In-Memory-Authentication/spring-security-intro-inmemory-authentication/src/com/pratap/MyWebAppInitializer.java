package com.pratap;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.pratap.config.AppConfig;
import com.pratap.config.RootConfig;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        //return new Class<?>[] { RootConfig.class };
    	return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { AppConfig.class };	// java config classes for spring beans
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
// Create a DisptacherServlet
// A spring container for the DisptacherServlet
// A root container ( optional )
