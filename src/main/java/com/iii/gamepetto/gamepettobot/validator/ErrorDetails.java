package com.iii.gamepetto.gamepettobot.validator;

import java.util.Date;

public abstract class ErrorDetails {

	private Date timestamp;
	private String message;

	public ErrorDetails() {}

	public ErrorDetails(String msg, Date timestamp) {
		this.message = msg;
		this.timestamp = timestamp;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
