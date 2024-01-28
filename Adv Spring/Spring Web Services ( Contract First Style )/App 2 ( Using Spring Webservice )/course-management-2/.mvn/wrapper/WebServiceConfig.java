package com.pratap.soap;

import javax.servlet.Servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

// Enable spring webservice
// Spring configuration

@Configuration
@EnableWs
public class WebServiceConfig {
	
	// MessageDispatcherServlet
		// ApplicationContext
	// url -> /ws/*
	
	@Bean
	public ServletRegistrationBean<Servlet>  messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<Servlet>(messageDispatcherServlet , "/ws/*");
	}
	
	// courses.wsdl
	// /ws/courses.wsdl
	
		// PortType	- CoursePort
		// Namespace - http://pratap.com/courses
	// coursedetails.xsd
	@Bean(name = "courses")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema courseSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		// PortType - CoursePort
		definition.setPortTypeName("CoursePort");
		// Namespace - http://pratap.com/courses
		definition.setTargetNamespace("http://pratap.com/courses");
		// uri - /ws
		definition.setLocationUri("/ws");
		// schema
		definition.setSchema(courseSchema);
		
		return definition;
	}
	
	@Bean
	public XsdSchema coursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("coursedetails.xsd"));
	}	
}
