package com.iii.gamepetto.gamepettobot.model.api.response;

import java.io.Serializable;

public class GuildResponse implements Serializable {

	private final static long serialVersionUID = 124692315820657064L;
	private Long id;
	private String guildId;
	private String botPrefix;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGuildId() {
		return guildId;
	}

	public void setGuildId(String guildId) {
		this.guildId = guildId;
	}

	public String getBotPrefix() {
		return botPrefix;
	}

	public void setBotPrefix(String botPrefix) {
		this.botPrefix = botPrefix;
	}
}
