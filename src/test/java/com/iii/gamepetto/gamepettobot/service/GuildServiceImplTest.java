package com.iii.gamepetto.gamepettobot.service;

import com.iii.gamepetto.gamepettobot.client.GuildRestClient;
import com.iii.gamepetto.gamepettobot.model.api.request.GuildRequest;
import com.iii.gamepetto.gamepettobot.model.api.response.GuildResponse;
import net.dv8tion.jda.api.entities.Guild;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class GuildServiceImplTest {

	@Mock
	GuildRestClient guildRestClient;
	@InjectMocks
	GuildService sut;

	@BeforeEach
	void setup() {
		this.sut = new GuildServiceImpl();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void registerGuildShouldReturnGuildResponseWithAppropriateFields() {
		//given
		Guild mockGuild = mock(Guild.class);
		String guildId = "test";
		GuildResponse response = new GuildResponse();
		response.setGuildId(guildId);
		Mockito.when(this.guildRestClient.registerGuild(any(GuildRequest.class))).thenReturn(response);

		//when
		GuildResponse result = this.sut.registerGuild(mockGuild);

		//then
		then(this.guildRestClient).should(times(1)).registerGuild(any(GuildRequest.class));
		assertThat(result.getGuildId(), is(guildId));
	}

	@Test
	void deleteGuildShouldReturnGuildId() {
		//given
		String guildId = "test";
		Mockito.when(this.guildRestClient.deleteGuild(guildId)).thenReturn(guildId);

		//when
		String result = this.sut.deleteGuild(guildId);

		//then
		then(this.guildRestClient).should(times(1)).deleteGuild(anyString());
		assertThat(result, is(guildId));
	}
}
