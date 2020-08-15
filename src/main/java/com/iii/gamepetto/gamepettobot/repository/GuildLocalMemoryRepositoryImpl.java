package com.iii.gamepetto.gamepettobot.repository;

import com.iii.gamepetto.gamepettobot.model.api.GuildPrefix;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class GuildLocalMemoryRepositoryImpl implements GuildRepository {

	Map<String, String> prefixesMap = new HashMap<>();

	@Override
	public String getPrefix(String guildId) {
		return this.prefixesMap.get(guildId);
	}

	@Override
	public void saveAllPrefixes(Map<String, String> prefixesMap) {
		this.prefixesMap.putAll(prefixesMap);
	}

	@Override
	public void savePrefix(GuildPrefix guildPrefix) {
		this.prefixesMap.put(guildPrefix.getGuildId(), guildPrefix.getBotPrefix());
	}

	@Override
	public void deletePrefix(String guildId) {
		this.prefixesMap.remove(guildId);
	}
}
