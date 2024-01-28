package com.pratap.global;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class GlobalWebDataBinderAdvice {
	@InitBinder
	public void initBinder(WebDataBinder binder) {

		System.out.println("in initbinder " + binder);
		// don't perform auto binding for this field
		// binder.setDisallowedFields("studentDOB"); 

		// Registering supported Date format
		SimpleDateFormat format = new SimpleDateFormat("yyyy--mm--dd");
		binder.registerCustomEditor(Date.class, "studentDOB", new CustomDateEditor(format, false));

	}
}
