package com.iii.gamepetto.gamepettobot.event;

import com.iii.gamepetto.gamepettobot.service.GuildService;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
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

}
