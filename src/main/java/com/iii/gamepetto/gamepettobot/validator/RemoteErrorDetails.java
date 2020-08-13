package com.iii.gamepetto.gamepettobot.validator;

import java.util.ArrayList;
import java.util.List;

public class RemoteErrorDetails extends ErrorDetails {

	private List<FieldError> fieldErrors = new ArrayList<>();

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
}
