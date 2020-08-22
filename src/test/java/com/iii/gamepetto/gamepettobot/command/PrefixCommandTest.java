package com.iii.gamepetto.gamepettobot.command;

import com.iii.gamepetto.gamepettobot.exception.RemoteValidationException;
import com.iii.gamepetto.gamepettobot.model.api.GuildPrefix;
import com.iii.gamepetto.gamepettobot.service.GuildService;
import com.iii.gamepetto.gamepettobot.validator.FieldError;
import com.iii.gamepetto.gamepettobot.validator.RemoteErrorDetails;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class PrefixCommandTest {

	@Mock
	GuildService guildService;
	@InjectMocks
	PrefixCommand sut;
	Message incomingMessage;

	@BeforeEach
	void setUp() {
		this.sut = new PrefixCommand();
		MockitoAnnotations.initMocks(this);
		this.incomingMessage = mock(Message.class);
		Guild guild = mock(Guild.class);
		MessageChannel messageChannel = mock(MessageChannel.class);
		MessageAction messageAction = mock(MessageAction.class);
		String guildId = "1234";

		when(this.incomingMessage.getGuild()).thenReturn(guild);
		when(this.incomingMessage.getGuild().getId()).thenReturn(guildId);
		when(this.incomingMessage.getChannel()).thenReturn(messageChannel);
		when(this.incomingMessage.getChannel().sendMessage(anyString())).thenReturn(messageAction);
	}

	@Test
	void executeShouldCallChangePrefixMethod() {
		//given
		String prefix = "!";
		String usedAlias = "prefix";
		String parameter = "%";

		//when
		this.sut.execute(this.incomingMessage, prefix, usedAlias, parameter);

		//then
		then(this.guildService).should(times(1)).changePrefix(any(GuildPrefix.class));
	}

	@Test
	void executeShouldDisplayExpectedMessageOnSuccess() {
		//given
		String prefix = "!";
		String usedAlias = "prefix";
		String parameter = "&";
		String expectedMessage = "Prefix changed to: " + parameter;

		//when
		this.sut.execute(this.incomingMessage, prefix, usedAlias, parameter);

		//then
		then(this.incomingMessage.getChannel()).should(times(1)).sendMessage(expectedMessage);
	}

	@Test
	void executeShouldDisplayExpectedMessageOnFailure() {
		//given
		String prefix = "!";
		String usedAlias = "prefix";
		String parameter = "$";
		String errorMsg = "Random error";

		FieldError fieldError = new FieldError();
		fieldError.setMessage(errorMsg);
		RemoteErrorDetails errorDetails = new RemoteErrorDetails();
		errorDetails.setFieldErrors(List.of(fieldError));
		RemoteValidationException ex = mock(RemoteValidationException.class);

		willThrow(ex).given(this.guildService).changePrefix(any(GuildPrefix.class));
		given(ex.getErrorDetails()).willReturn(errorDetails);

		//when
		this.sut.execute(this.incomingMessage, prefix, usedAlias, parameter);

		//then
		then(this.incomingMessage.getChannel()).should(times(1)).sendMessage(errorMsg);
	}
}
