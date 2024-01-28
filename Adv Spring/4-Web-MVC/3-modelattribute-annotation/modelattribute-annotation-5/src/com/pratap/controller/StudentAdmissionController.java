package com.pratap.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pratap.model.Student;

@Controller
public class StudentAdmissionController {

	/*
	@InitBinder
	public void initBinder(WebDataBinder binder) {

		System.out.println("in initbinder " + binder);
		// binder.setDisallowedFields("studentDOB"); // don't perform auto binding for this field

		// Registering supported Date format
		SimpleDateFormat format = new SimpleDateFormat("yyyy--mm--dd");
		binder.registerCustomEditor(Date.class, "studentDOB", new CustomDateEditor(format, false));

	}
	*/

	@RequestMapping(value = "/admissionForm.html", method = RequestMethod.GET)
	public ModelAndView getAdmissionForm() {
		ModelAndView mav = new ModelAndView("AdmissionForm");
		return mav;
	}

	@RequestMapping(value = "submitAdmissionForm.html", method = RequestMethod.POST)
	public ModelAndView submitAdmissionForm(@ModelAttribute("student") Student student, BindingResult result) {

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("AdmissionForm");
			mav.addObject("formerrors", "Supply valid value");
			return mav;
		}

		ModelAndView mav = new ModelAndView("AdmissionSuccess");
		mav.addObject("msg", "Details Submitted by you ");

		return mav;
	}

	@PostMapping("/create")
	public void handlePost(ServletResponse res) {
		System.out.println("in handlepost");
	}
}
