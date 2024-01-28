package com.pratap.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pratap.model.Student;

@Controller
public class StudentAdmissionController {

	@RequestMapping(value = "/admissionForm.html", method = RequestMethod.GET)
	public ModelAndView getAdmissionForm() {
		ModelAndView mav = new ModelAndView("AdmissionForm");
		return mav;
	}

	@ModelAttribute
	@RequestMapping(value = "submitAdmissionForm.html", method = RequestMethod.POST)
	public ModelAndView submitAdmissionForm(@Valid @ModelAttribute("student") Student student, BindingResult result) {
	
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("AdmissionForm");
			return mav;
		}
		
		ModelAndView mav = new ModelAndView("AdmissionSuccess");
		mav.addObject("msg", "Details Submitted by you ");

		return mav;
	}

}
