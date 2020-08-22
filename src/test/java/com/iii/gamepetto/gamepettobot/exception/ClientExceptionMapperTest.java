package com.iii.gamepetto.gamepettobot.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import java.io.ByteArrayInputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClientExceptionMapperTest {

	ClientExceptionMapper sut;

	@BeforeEach
	void setup() {
		this.sut = new ClientExceptionMapper();
	}

	@Test
	void toThrowableShouldReturnRemoteValidationExceptionWhenStatusIs400() {
		//given
		Response response = mock(Response.class);
		ByteArrayInputStream is = mock(ByteArrayInputStream.class);
		when(response.getStatus()).thenReturn(400);
		when(response.getEntity()).thenReturn(is);

		//when
		RuntimeException result = this.sut.toThrowable(response);

		//then
		assertThat(result, instanceOf(RemoteValidationException.class));
	}

	@Test
	void toThrowableShouldReturnNotFoundExceptionWhenStatusIs404() {
		//given
		Response response = mock(Response.class);
		ByteArrayInputStream is = mock(ByteArrayInputStream.class);
		when(response.getStatus()).thenReturn(404);
		when(response.getEntity()).thenReturn(is);

		//when
		RuntimeException result = this.sut.toThrowable(response);

		//then
		assertThat(result, instanceOf(NotFoundException.class));
	}

	@Test
	void toThrowableShouldReturnWebApplicationExceptionWhenStatusIsNotCovered() {
		//given
		Response response = mock(Response.class);
		ByteArrayInputStream is = mock(ByteArrayInputStream.class);
		when(response.getStatus()).thenReturn(-1);
		when(response.getEntity()).thenReturn(is);

		//when
		RuntimeException result = this.sut.toThrowable(response);

		//then
		assertThat(result, instanceOf(WebApplicationException.class));
	}
}
