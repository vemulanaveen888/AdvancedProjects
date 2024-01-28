package com.pratap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pratap.model.Student;

@Controller
public class StudentAdmissionController {

	public StudentAdmissionController() {
		System.out.println("in controller constructor");
	}

	@RequestMapping(value = "/admissionForm.html", method = RequestMethod.GET)
	public String getAdmissionForm() {
		System.out.println("in getAdmissionForm");
		return "AdmissionForm";
	}

	@RequestMapping(value = "submitAdmissionForm.html", method = RequestMethod.POST)
	public ModelAndView submitAdmissionForm(@ModelAttribute("s1") Student student) {

		ModelAndView mav = new ModelAndView("AdmissionSuccess");
		mav.addObject("msg", "Details Submitted by you ");

		return mav;
	}

	@ModelAttribute
	public void commonModelData(Model model) {
		model.addAttribute("headerMessage", "WElCome Here");
	}

}
