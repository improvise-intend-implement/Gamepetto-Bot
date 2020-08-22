package com.iii.gamepetto.gamepettobot.event;

import com.iii.gamepetto.gamepettobot.service.GuildService;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class GuildEventTest {

	@Mock
	GuildService guildService;
	@InjectMocks
	GuildEvent sut;

	@BeforeEach
	void setup() {
		this.sut = new GuildEvent();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void onGuildJoinShouldCallRegisterGuild() {
		//given
		GuildJoinEvent gje = mock(GuildJoinEvent.class);
		Guild guild = mock(Guild.class);
		when(gje.getGuild()).thenReturn(guild);

		//when
		this.sut.onGuildJoin(gje);

		//then
		then(this.guildService).should(times(1)).registerGuild(any(Guild.class));
	}

	@Test
	void onGuildLeaveShouldCallDeleteGuild() {
		//given
		GuildLeaveEvent gle = mock(GuildLeaveEvent.class);
		Guild guild = mock(Guild.class);
		String guildId = "test";
		when(gle.getGuild()).thenReturn(guild);
		when(guild.getId()).thenReturn(guildId);

		//when
		this.sut.onGuildLeave(gle);

		//then
		then(this.guildService).should(times(1)).deleteGuild(anyString());
	}
}
