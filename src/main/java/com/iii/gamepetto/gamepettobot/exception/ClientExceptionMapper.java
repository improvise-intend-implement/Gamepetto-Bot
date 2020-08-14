package com.iii.gamepetto.gamepettobot.exception;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;

public class ClientExceptionMapper implements ResponseExceptionMapper<RuntimeException> {

	@Override
	public RuntimeException toThrowable(Response response) {
		RuntimeException runtimeException;
		int status = response.getStatus();
		String responseBody = getBodyFromResponse(response.getEntity());
		switch(status) {
			case 400:
				runtimeException = new RemoteValidationException(responseBody);
				break;
			case 404:
				runtimeException = new NotFoundException("Couldn't find: " + responseBody);
				break;
			default:
				runtimeException = new WebApplicationException(status);
		}
		return runtimeException;
	}

	private String getBodyFromResponse(Object inputStream) {
		ByteArrayInputStream is = (ByteArrayInputStream) inputStream;
		byte[] bytes = new byte[is.available()];
		is.read(bytes, 0, is.available());
		return new String(bytes);
	}
}
