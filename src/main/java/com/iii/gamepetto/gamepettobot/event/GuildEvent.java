package com.iii.gamepetto.gamepettobot.event;

import com.iii.gamepetto.gamepettobot.model.api.request.GuildRequest;
import com.iii.gamepetto.gamepettobot.service.GuildService;
import io.quarkus.runtime.Startup;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Startup
public class GuildEvent extends ListenerAdapter {

	@Inject
	GuildService guildService;

	@Override
	public void onGuildJoin(GuildJoinEvent event) {
		System.out.println("joined guild");
		GuildRequest guildRequest = new GuildRequest();
		guildRequest.setGuildId(event.getGuild().getId());
		guildRequest.setName(event.getGuild().getName());
		guildRequest.setIcon(event.getGuild().getIconId());
		guildService.registerGuild(event.getGuild());
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		System.out.println("message");
		guildService.registerGuild(event.getGuild());
	}

}
