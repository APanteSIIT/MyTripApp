package com.myTrips.service;

import com.google.common.base.Joiner;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/*
This is a library used to create some powerful password rules without having to actually manually
implement them.
We will create the password validator PasswordConstraintValidator – and we'll define the rules for the password.
We'll create a new constraint violation here and disabling the default one as well – in case the password is not valid.
 */
//imported passay dependency in pom.xml file
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
	
	@Override
	public void initialize(ValidPassword arg0) {
	}
	
	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		PasswordValidator validator = new PasswordValidator(Arrays.asList(
				// at least 8 characters
				new LengthRule(8,16),
				
				// at least one upper-case character
				new CharacterRule(EnglishCharacterData.UpperCase, 1),
				
				// at least one lower-case character
				new CharacterRule(EnglishCharacterData.LowerCase, 1),
				
				// at least one digit character
				new CharacterRule(EnglishCharacterData.Digit, 1),
				
				// at least one symbol (special character)
				new CharacterRule(EnglishCharacterData.Special, 1),
				
				// no whitespace
				new WhitespaceRule()
		
		));
		RuleResult result = validator.validate(new PasswordData(password));
		if (result.isValid()) {
			return true;
		}
		List<String> messages = validator.getMessages(result);
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(
				Joiner.on(",").join(validator.getMessages(result)))
				.addConstraintViolation();

		
		return false;
	}
}