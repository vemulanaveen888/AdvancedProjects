package com.pratap.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.security.Principal;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.PushBuilder;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class RequestController {
	@GetMapping("/request1")
	public String handleRequest1(ServletRequest req, ServletResponse res) {
		return "";
	}

	@GetMapping("/request2")
	public String handleRequest2(WebRequest req) {
		return "";
	}

	@GetMapping("/request3")
	public String handleRequest3(NativeWebRequest req) {
		return "";
	}

	@GetMapping("/request4")
	public String handleRequest4(HttpSession session) {
		return "";
	}

	@GetMapping("/request5")
	public String handleRequest5(PushBuilder pb) {
		return "";
	}

	@GetMapping("/request6")
	public String handleRequest6(Principal p, Locale l, TimeZone tz, ZoneId zid) {
		return "";
	}

	@GetMapping("/request7")
	public String handleRequest7(InputStream is, Reader reader) {
		return "";
	}

	@GetMapping("/request8")
	public String handleRequest8(OutputStream os, Writer out) {
		return "";
	}

	@GetMapping("/request9")
	public String handleRequest9(HttpMethod method) {
		return "";
	}

	@GetMapping("/request10")
	public String handleRequest10(RequestEntity entity) {
		return "";
	}

	@GetMapping("/request11")
	public String handleRequest11(Map model) {
		// populate the model object
		return "";
	}

	@GetMapping("/request12")
	public String handleRequest12(Model model) {
		// populate the model object
		return "";
	}

	@GetMapping("/request12")
	public String handleRequest12(ModelMap model) {
		// populate the model object
		return "";
	}

	@GetMapping("/request13")
	public String handleRequest13(RedirectAttributes ra) {

		return "";
	}

	@GetMapping("/request14")
	public String handleRequest14(@ModelAttribute Student student, BindingResult result) {

		return "";
	}

	@GetMapping("/request15")
	public String handleRequest15(UriComponentsBuilder builder) {
		// Allows you to programatically build URI
		return "";
	}

	@GetMapping("/request16")
	public String handleRequest16(@PathVariable int x) {
		return "";
	}

	@GetMapping("/request17")
	public String handleRequest17(String input) { // Request parameter
		return "";
	}

	@GetMapping("/request18")
	public String handleRequest18(Student st) { // Model Attribute
		return "";
	}

	/* ------------------------------ */
	@GetMapping("/request19")
	public ResponseEntity handleRequest19( ) {
		// The return value that specifies the full response (including HTTP headers and body) 
		// is to be converted through HttpMessageConverter implementations and written to the response
		return null;
	}
	
	@GetMapping("/request20")
	public HttpHeaders handleRequest20( ) {
		// For returning a response with headers and no body.
		return null;
	}
	@GetMapping("/request21")
	public String handleRequest21( ) {
		// view name
		return "hellpage";
	}
	
	@GetMapping("/request22")
	public View handleRequest22( ) {
		// view object
		return new JstlView("/WEB-INF/jsps/hellopage.jsp");
	}
	
	@GetMapping("/request23")
	public Map handleRequest23( ) {
		// Attributes to be added to the implicit model, 
		// with the view name implicitly determined through a RequestToViewNameTranslator.
		return null;
	}
	
	@GetMapping("/request24")
	public Model handleRequest24( ) {
		// Attributes to be added to the implicit model, 
		// with the view name implicitly determined through a RequestToViewNameTranslator.
		return null;
	}
	
	@GetMapping("/request25")
	public ModelAndView handleRequest25( ) {
		// The view and model attributes to use and, optionally, a response status.
		return null;
	}
	
	@GetMapping("/request26")
	public void handleRequest26( ) {
		// If none of the above is true, a void return type can also indicate “no response body” for REST controllers 
		// or a default view name selection for HTML controllers. RequestToViewNameTranslator
		return ;
	}
	
	@GetMapping("/request27")
	public void handleRequest27( ServletResponse res ) {
		// A method with a void return type (or null return value) is considered to have 
		// fully handled the response if it also has a ServletResponse
		return ;
	}
}
