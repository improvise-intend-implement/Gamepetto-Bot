package com.iii.gamepetto.gamepettobot.command;

import com.iii.gamepetto.gamepettobot.service.GuildService;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.kautler.command.api.prefix.PrefixProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CustomPrefixProviderTest {

	@Mock
	GuildService guildService;
	@InjectMocks
	PrefixProvider<Message> sut;

	@BeforeEach
	void setup() {
		this.sut = new CustomPrefixProvider();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void getCommandPrefixShouldReturnPrefixFromRepositoryWhenExists() {
		//given
		String guildId = "1234";
		String prefix = "xD";
		Message message = mock(Message.class);
		Guild guild = mock(Guild.class);
		given(message.getGuild()).willReturn(guild);
		given(message.getGuild().getId()).willReturn(guildId);
		given(this.guildService.getPrefix(guildId)).willReturn(prefix);

		//when
		String result = this.sut.getCommandPrefix(message);

		//then
		assertThat(result, is(prefix));
	}

	@Test
	void getCommandPrefixShouldReturnDefaultPrefixWhenPrefixFromRepositoryIsNull() {
		//given
		String guildId = "1234";
		String defaultPrefix = "!";
		Message message = mock(Message.class);
		Guild guild = mock(Guild.class);
		Whitebox.setInternalState(this.sut, "defaultPrefix", defaultPrefix);
		given(message.getGuild()).willReturn(guild);
		given(message.getGuild().getId()).willReturn(guildId);
		given(this.guildService.getPrefix(guildId)).willReturn(null);

		//when
		String result = this.sut.getCommandPrefix(message);

		//then
		assertThat(result, is(defaultPrefix));
	}
}
