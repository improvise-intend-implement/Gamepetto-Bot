package com.iii.gamepetto.gamepettobot.exception;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import java.io.ByteArrayInputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.mock;

class ClientExceptionMapperTest {

	ClientExceptionMapper sut = new ClientExceptionMapper();

	@Test
	void toThrowableShouldReturnRemoteValidationExceptionWhenStatusIs400() {
		//given
		Response response = mock(Response.class);
		ByteArrayInputStream is = mock(ByteArrayInputStream.class);
		Mockito.when(response.getStatus()).thenReturn(400);
		Mockito.when(response.getEntity()).thenReturn(is);

		//when
		RuntimeException result = this.sut.toThrowable(response);

		//then
		assertThat(result, instanceOf(RemoteValidationException.class));
	}

	@Test
	void toThrowableShouldReturnWebApplicationExceptionWhenStatusIsNotCovered() {
		//given
		Response response = mock(Response.class);
		ByteArrayInputStream is = mock(ByteArrayInputStream.class);
		Mockito.when(response.getStatus()).thenReturn(-1);
		Mockito.when(response.getEntity()).thenReturn(is);

		//when
		RuntimeException result = this.sut.toThrowable(response);

		//then
		assertThat(result, instanceOf(WebApplicationException.class));
	}
}
