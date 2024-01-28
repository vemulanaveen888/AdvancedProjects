package com.pratap;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class CustomerFieldSetMapper implements FieldSetMapper<Account> {

	@Override
	public Account mapFieldSet(FieldSet fieldSet) throws BindException {
		// TODO Auto-generated method stub
		return new Account(fieldSet.readInt("accid"), 
				fieldSet.readString("name"), 
				fieldSet.readDouble("balance"));
	}

}
