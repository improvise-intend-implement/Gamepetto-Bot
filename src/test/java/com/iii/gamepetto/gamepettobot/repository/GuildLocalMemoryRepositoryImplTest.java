package com.iii.gamepetto.gamepettobot.repository;

import com.iii.gamepetto.gamepettobot.model.api.GuildPrefix;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class GuildLocalMemoryRepositoryImplTest {

	GuildRepository sut = new GuildLocalMemoryRepositoryImpl();

	@Test
	void getPrefixShouldReturnNullValueWhenKeyDoesntExist() {
		//given
		//when
		String result = this.sut.getPrefix("any");

		//then
		assertThat(result, nullValue());
	}

	@Test
	void savePrefixShouldSavePrefixForLaterUsage() {
		//given
		String guildId = "1";
		String botPrefix = "!gp";
		GuildPrefix guildPrefix = new GuildPrefix(guildId, botPrefix);

		//when
		this.sut.savePrefix(guildPrefix);

		//then
		assertThat(this.sut.getPrefix(guildId), notNullValue());
	}

	@Test
	void savePrefixShouldUpdateExistingKey() {
		//given
		String guildId = "1";
		String botPrefixBeforeUpdate = "!gp";
		String botPrefixAfterUpdate = "@";
		GuildPrefix guildPrefix = new GuildPrefix(guildId, botPrefixBeforeUpdate);
		this.sut.savePrefix(guildPrefix);
		guildPrefix.setBotPrefix(botPrefixAfterUpdate);

		//when
		this.sut.savePrefix(guildPrefix);

		//then
		assertThat(this.sut.getPrefix(guildId), is(botPrefixAfterUpdate));
	}

	@Test
	void getPrefixShouldReturnStringValueWhenKeyExists() {
		//given
		String guildId = "1";
		String botPrefix = "!gp";
		GuildPrefix guildPrefix = new GuildPrefix(guildId, botPrefix);
		this.sut.savePrefix(guildPrefix);

		//when
		String result = this.sut.getPrefix(guildId);

		//then
		assertThat(result, notNullValue());
		assertThat(result, is(botPrefix));
	}

	@Test
	void saveAllPrefixesShouldSavePrefixesForLaterUsage() {
		//given
		String guildId1 = "1";
		String botPrefix1 = "!gp";
		String guildId2 = "2";
		String botPrefix2 = ":";
		Map<String, String> prefixes = new HashMap<>();
		prefixes.put(guildId1, botPrefix1);
		prefixes.put(guildId2, botPrefix2);

		//when
		this.sut.saveAllPrefixes(prefixes);

		//then
		assertThat(this.sut.getPrefix(guildId1), is(botPrefix1));
		assertThat(this.sut.getPrefix(guildId2), is(botPrefix2));
	}

	@Test
	void deletePrefixShouldReturnNullValueWhenGetPrefix() {
		//given
		String guildId = "1";
		String botPrefix = "!gp";
		GuildPrefix guildPrefix = new GuildPrefix(guildId, botPrefix);
		this.sut.savePrefix(guildPrefix);

		//when
		this.sut.deletePrefix(guildId);

		//then
		assertThat(this.sut.getPrefix(guildId), nullValue());
	}

}
