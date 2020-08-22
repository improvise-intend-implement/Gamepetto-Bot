package com.iii.gamepetto.gamepettobot.model.api.request;

import java.io.Serializable;

public class BotPrefix implements Serializable {

	private static final long serialVersionUID = -2129460707534138969L;
	private String botPrefix;

	public BotPrefix() {}

	public BotPrefix(String botPrefix) {
		this.botPrefix = botPrefix;
	}

	public String getBotPrefix() {
		return botPrefix;
	}

	public void setBotPrefix(String botPrefix) {
		this.botPrefix = botPrefix;
	}
}
