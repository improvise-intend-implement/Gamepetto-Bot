package com.iii.gamepetto.gamepettobot.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iii.gamepetto.gamepettobot.validator.RemoteErrorDetails;

public class RemoteValidationException extends RuntimeException {

	private RemoteErrorDetails errorDetails;

	public RemoteValidationException(String jsonMsg) {
		super(jsonMsg);
		try {
			this.errorDetails = new ObjectMapper().readValue(jsonMsg, RemoteErrorDetails.class);
		} catch (JsonProcessingException ex) {
			this.errorDetails = null;
		}
	}

	public RemoteErrorDetails getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(RemoteErrorDetails errorDetails) {
		this.errorDetails = errorDetails;
	}
}
