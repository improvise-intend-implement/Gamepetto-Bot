package com.iii.gamepetto.gamepettobot.repository;

import com.iii.gamepetto.gamepettobot.model.api.GuildPrefix;

import java.util.Map;

public interface GuildRepository {

	String getPrefix(String guildId);

	void saveAllPrefixes(Map<String, String> prefixesMap);

	void savePrefix(GuildPrefix guildPrefix);

	void deletePrefix(String guildId);
}
