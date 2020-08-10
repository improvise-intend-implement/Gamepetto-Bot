package com.iii.gamepetto.gamepettobot.model.api.response;

public class GuildResponse{

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
