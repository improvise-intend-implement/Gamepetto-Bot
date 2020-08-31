package com.iii.gamepetto.gamepettobot.command;

import com.iii.gamepetto.gamepettobot.exception.RemoteValidationException;
import com.iii.gamepetto.gamepettobot.model.api.GuildPrefix;
import com.iii.gamepetto.gamepettobot.restriction.ServerOwnerRestriction;
import com.iii.gamepetto.gamepettobot.service.GuildService;
import net.dv8tion.jda.api.entities.Message;
import net.kautler.command.api.Command;
import net.kautler.command.api.annotation.RestrictedTo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@RestrictedTo(ServerOwnerRestriction.class)
public class PrefixCommand implements Command<Message> {

	@Inject
	GuildService guildService;

	@Override
	public void execute(Message incomingMessage, String prefix, String usedAlias, String parameterString) {
		String guildId = incomingMessage.getGuild().getId();
		GuildPrefix guildPrefix = new GuildPrefix(guildId, parameterString);
		try {
			this.guildService.changePrefix(guildPrefix);
			String msg = "Prefix changed to: " + parameterString;
			incomingMessage.getChannel().sendMessage(msg).queue();
		} catch (RemoteValidationException ex) {
			String error = ex.getErrorDetails().getFieldErrors().get(0).getMessage();
			incomingMessage.getChannel().sendMessage(error).queue();
		}
	}
}
