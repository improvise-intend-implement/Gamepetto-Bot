package com.iii.gamepetto.gamepettobot.service;

import com.iii.gamepetto.gamepettobot.client.GuildRestClient;
import com.iii.gamepetto.gamepettobot.model.api.request.GuildRequest;
import com.iii.gamepetto.gamepettobot.model.api.response.GuildResponse;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import net.dv8tion.jda.api.entities.Guild;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@QuarkusTest
class GuildServiceImplTest {

	@InjectMock
	@RestClient
	GuildRestClient guildRestClient;
	@Inject
	GuildService sut;

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
		assertThat(result.getGuildId(), is(guildId));
	}
}
