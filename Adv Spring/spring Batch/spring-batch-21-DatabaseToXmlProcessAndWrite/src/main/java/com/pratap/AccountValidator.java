package com.pratap;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

public class AccountValidator implements Validator<Account>{
	public void validate(Account acc) throws ValidationException {
		if( acc.getName().startsWith("N")) {
			throw new ValidationException("first name begin with a , invlaid data ");
		}	
	}
}
