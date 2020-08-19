package com.iii.gamepetto.gamepettobot.command;

import net.dv8tion.jda.api.entities.Message;
import net.kautler.command.api.Command;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PingCommand implements Command<Message> {

	@Override
	public void execute(Message incomingMessage, String prefix, String usedAlias, String parameterString) {
		incomingMessage
				.getChannel()
				.sendMessage("pong: " + parameterString)
				.queue(null, throwable -> System.out.println("change to logger"));
	}
}
