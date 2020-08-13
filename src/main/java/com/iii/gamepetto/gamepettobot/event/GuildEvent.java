package com.iii.gamepetto.gamepettobot.event;

import com.iii.gamepetto.gamepettobot.service.GuildService;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class GuildEvent extends ListenerAdapter {

	@Inject
	GuildService guildService;

	@Override
	public void onGuildJoin(GuildJoinEvent event) {
		guildService.registerGuild(event.getGuild());
	}

}
