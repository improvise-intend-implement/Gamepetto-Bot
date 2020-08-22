package com.iii.gamepetto.gamepettobot.service;

import com.iii.gamepetto.gamepettobot.client.GuildRestClient;
import com.iii.gamepetto.gamepettobot.model.api.GuildPrefix;
import com.iii.gamepetto.gamepettobot.model.api.request.BotPrefix;
import com.iii.gamepetto.gamepettobot.model.api.request.GuildRequest;
import com.iii.gamepetto.gamepettobot.model.api.response.GuildResponse;
import com.iii.gamepetto.gamepettobot.repository.GuildRepository;
import net.dv8tion.jda.api.entities.Guild;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class GuildServiceImplTest {

	@Mock
	GuildRestClient guildRestClient;
	@Mock
	GuildRepository guildRepository;
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
		when(this.guildRestClient.registerGuild(any(GuildRequest.class))).thenReturn(response);

		//when
		GuildResponse result = this.sut.registerGuild(mockGuild);

		//then
		then(this.guildRestClient).should(times(1)).registerGuild(any(GuildRequest.class));
		assertThat(result.getGuildId(), is(guildId));
	}

	@Test
	void registerGuildShouldCallSavePrefixFromGuildRepository() {
		//given
		Guild mockGuild = mock(Guild.class);
		String guildId = "test";
		GuildResponse response = new GuildResponse();
		response.setGuildId(guildId);
		when(this.guildRestClient.registerGuild(any(GuildRequest.class))).thenReturn(response);

		//when
		this.sut.registerGuild(mockGuild);

		//then
		then(this.guildRepository).should(times(1)).savePrefix(any(GuildPrefix.class));
	}

	@Test
	void deleteGuildShouldReturnGuildId() {
		//given
		String guildId = "test";
		when(this.guildRestClient.deleteGuild(guildId)).thenReturn(guildId);

		//when
		String result = this.sut.deleteGuild(guildId);

		//then
		then(this.guildRestClient).should(times(1)).deleteGuild(anyString());
		assertThat(result, is(guildId));
	}

	@Test
	void deleteGuildShouldCallDeletePrefixFromGuildRepository() {
		//given
		//when
		this.sut.deleteGuild("test");

		//then
		then(this.guildRepository).should(times(1)).deletePrefix(anyString());
	}

	@Test
	void loadAllPrefixesShouldCallSaveAllPrefixesFromGuildRepository() {
		//given
		//when
		this.sut.loadAllPrefixes();

		//then
		then(this.guildRepository).should(times(1)).saveAllPrefixes(anyMap());
	}

	@Test
	void savePrefixShouldCallSavePrefixFromGuildRepository() {
		//given
		//when
		this.sut.savePrefix(new GuildPrefix());

		//then
		then(this.guildRepository).should(times(1)).savePrefix(any(GuildPrefix.class));
	}

	@Test
	void changePrefixShouldCallAppropriateMethods() {
		//given
		String id = "1234";
		String botPrefix = "!";
		GuildPrefix guildPrefix = new GuildPrefix(id, botPrefix);

		//when
		this.sut.changePrefix(guildPrefix);

		//then
		then(this.guildRestClient).should(times(1)).changePrefix(anyString(), any(BotPrefix.class));
		then(this.guildRepository).should(times(1)).savePrefix(any(GuildPrefix.class));
	}

	@Test
	void getPrefixShouldReturnPrefix() {
		//given
		String guildId = "1234";
		String prefix = "!gp ";
		given(this.guildRepository.getPrefix(guildId)).willReturn(prefix);

		//when
		String result = this.sut.getPrefix(guildId);

		//then
		assertThat(result, is(prefix));
	}
}
