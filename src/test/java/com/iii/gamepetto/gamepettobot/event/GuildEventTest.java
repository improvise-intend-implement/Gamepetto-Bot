package com.iii.gamepetto.gamepettobot.event;

import com.iii.gamepetto.gamepettobot.service.GuildService;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.NotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

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
		Mockito.when(gje.getGuild()).thenReturn(guild);

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
		Mockito.when(gle.getGuild()).thenReturn(guild);
		Mockito.when(guild.getId()).thenReturn(guildId);

		//when
		this.sut.onGuildLeave(gle);

		//then
		then(this.guildService).should(times(1)).deleteGuild(anyString());
	}

	@Test
	void onGuildLeaveShouldCatchNotFoundException() {
		//given
		GuildLeaveEvent gle = mock(GuildLeaveEvent.class);
		Guild guild = mock(Guild.class);
		String guildId = "test";
		Mockito.when(gle.getGuild()).thenReturn(guild);
		Mockito.when(guild.getId()).thenReturn(guildId);
		Mockito.when(this.guildService.deleteGuild(guildId)).thenThrow(NotFoundException.class);

		//when
		this.sut.onGuildLeave(gle);


	}

}
