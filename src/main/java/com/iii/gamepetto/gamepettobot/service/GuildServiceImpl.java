package com.iii.gamepetto.gamepettobot.service;

import com.iii.gamepetto.gamepettobot.client.GuildRestClient;
import com.iii.gamepetto.gamepettobot.model.api.GuildPrefix;
import com.iii.gamepetto.gamepettobot.model.api.request.BotPrefix;
import com.iii.gamepetto.gamepettobot.model.api.request.GuildRequest;
import com.iii.gamepetto.gamepettobot.model.api.response.GuildResponse;
import com.iii.gamepetto.gamepettobot.repository.GuildRepository;
import net.dv8tion.jda.api.entities.Guild;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Map;

@Dependent
public class GuildServiceImpl implements GuildService {

	@Inject
	@RestClient
	GuildRestClient guildRestClient;
	@Inject
	GuildRepository guildRepository;

	@Override
	public GuildResponse registerGuild(Guild guild) {
		GuildRequest guildRequest = new GuildRequest();
		guildRequest.setGuildId(guild.getId());
		guildRequest.setName(guild.getName());
		guildRequest.setIcon(guild.getIconId());
		GuildResponse guildResponse = this.guildRestClient.registerGuild(guildRequest);
		GuildPrefix guildPrefix = new GuildPrefix(guildResponse.getGuildId(), guildResponse.getBotPrefix());
		this.guildRepository.savePrefix(guildPrefix);
		return guildResponse;
	}

	@Override
	public String deleteGuild(String guildId) {
		this.guildRepository.deletePrefix(guildId);
		return this.guildRestClient.deleteGuild(guildId);
	}

	@Override
	public void loadAllPrefixes() {
		Map<String, String> prefixes = this.guildRestClient.getAllPrefixes();
		this.guildRepository.saveAllPrefixes(prefixes);
	}

	@Override
	public void savePrefix(GuildPrefix guildPrefix) {
		this.guildRepository.savePrefix(guildPrefix);
	}

	@Override
	public void changePrefix(GuildPrefix guildPrefix) {
		this.guildRestClient.changePrefix(guildPrefix.getGuildId(), new BotPrefix(guildPrefix.getBotPrefix()));
		this.guildRepository.savePrefix(guildPrefix);
	}

	@Override
	public String getPrefix(String guildId) {
		return this.guildRepository.getPrefix(guildId);
	}
}
