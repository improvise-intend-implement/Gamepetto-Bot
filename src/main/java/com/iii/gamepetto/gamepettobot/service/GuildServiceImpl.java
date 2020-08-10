package com.iii.gamepetto.gamepettobot.service;

import com.iii.gamepetto.gamepettobot.client.GuildRestClient;
import com.iii.gamepetto.gamepettobot.model.api.request.GuildRequest;
import com.iii.gamepetto.gamepettobot.model.api.response.GuildResponse;
import net.dv8tion.jda.api.entities.Guild;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GuildServiceImpl implements GuildService {

	@Inject
	@RestClient
	GuildRestClient guildRestClient;

	public GuildResponse registerGuild(Guild guild) {
		GuildRequest guildRequest = new GuildRequest();
		guildRequest.setGuildId(guild.getId());
		guildRequest.setName(guild.getName());
		guildRequest.setIcon(guild.getIconId());
		return guildRestClient.registerGuild(new GuildRequest());
	}

}
