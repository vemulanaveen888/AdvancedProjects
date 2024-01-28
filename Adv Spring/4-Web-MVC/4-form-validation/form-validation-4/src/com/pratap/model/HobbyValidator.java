package com.pratap.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HobbyValidator implements ConstraintValidator<IsValidHobby, String> {

	private String listOfValidHobbies;

	public void initialize(IsValidHobby isValidHobby) {
		listOfValidHobbies = isValidHobby.listOfValidHobbies();
	}

	public boolean isValid(String studentHobby, ConstraintValidatorContext ctx) {

		// context 1
		/*
		 * if (studentHobby.matches("Music|Football|Cricket|Hockey")) { return true; }
		 * else { return false; }
		 */

		
		// context 3
		if (studentHobby == null)
			return false;

		if (studentHobby.matches(listOfValidHobbies)) {
			return true;
		} else {
			return false;
		}

	}
}
