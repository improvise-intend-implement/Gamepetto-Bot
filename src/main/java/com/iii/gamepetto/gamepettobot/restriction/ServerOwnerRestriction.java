package com.iii.gamepetto.gamepettobot.restriction;

import net.dv8tion.jda.api.entities.Message;
import net.kautler.command.api.restriction.Restriction;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ServerOwnerRestriction implements Restriction<Message> {

	@Override
	public boolean allowCommand(Message message) {
		boolean allowed = false;
		if (message.getMember() != null && message.getMember().isOwner()) {
			allowed = true;
		} else {
			message.getChannel().sendMessage("Command available only for the server owner.").queue();
		}
		return allowed;
	}
}
