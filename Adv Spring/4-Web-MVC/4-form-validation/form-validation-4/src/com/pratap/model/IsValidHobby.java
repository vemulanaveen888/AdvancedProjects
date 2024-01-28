package com.pratap.model;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = HobbyValidator.class)
public @interface IsValidHobby {
	
	String listOfValidHobbies() default "Music|Football|Cricket|Hockey";
	
	String message() default "please provide a valid hobby;"
			+ "acepted hobbies are Music , Football , Cricket and Hockey";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
