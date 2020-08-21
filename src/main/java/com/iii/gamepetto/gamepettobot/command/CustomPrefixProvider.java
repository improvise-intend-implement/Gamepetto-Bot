package com.iii.gamepetto.gamepettobot.command;

import com.iii.gamepetto.gamepettobot.service.GuildService;
import net.dv8tion.jda.api.entities.Message;
import net.kautler.command.api.prefix.PrefixProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CustomPrefixProvider implements PrefixProvider<Message> {

	@Inject
	GuildService guildService;

	@Override
	public String getCommandPrefix(Message message) {
		String guildId = message.getGuild().getId();
		String prefix = this.guildService.getPrefix(guildId);
		return prefix != null ? prefix : "!";
	}
}
