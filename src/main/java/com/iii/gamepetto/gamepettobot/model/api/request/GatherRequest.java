package com.iii.gamepetto.gamepettobot.model.api.request;

import java.io.Serializable;
import java.util.List;

public class GatherRequest implements Serializable {

	private static final long serialVersionUID = -4958096728326281513L;
	private String guildId;
	private String name;
	private String shortName;
	private String channelId;
	private Integer playersPerTeam;
	private Integer mapsNumber;
	private Long gameId;
	private List<Long> mapsIds;
	private Boolean mapsRandom;
	private Boolean captainRolePriority;
	private Boolean allAllowed;

	public String getGuildId() {
		return guildId;
	}

	public void setGuildId(String guildId) {
		this.guildId = guildId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Integer getPlayersPerTeam() {
		return playersPerTeam;
	}

	public void setPlayersPerTeam(Integer playersPerTeam) {
		this.playersPerTeam = playersPerTeam;
	}

	public Integer getMapsNumber() {
		return mapsNumber;
	}

	public void setMapsNumber(Integer mapsNumber) {
		this.mapsNumber = mapsNumber;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public List<Long> getMapsIds() {
		return mapsIds;
	}

	public void setMapsIds(List<Long> mapsIds) {
		this.mapsIds = mapsIds;
	}

	public Boolean getMapsRandom() {
		return mapsRandom;
	}

	public void setMapsRandom(Boolean mapsRandom) {
		this.mapsRandom = mapsRandom;
	}

	public Boolean getCaptainRolePriority() {
		return captainRolePriority;
	}

	public void setCaptainRolePriority(Boolean captainRolePriority) {
		this.captainRolePriority = captainRolePriority;
	}

	public Boolean getAllAllowed() {
		return allAllowed;
	}

	public void setAllAllowed(Boolean allAllowed) {
		this.allAllowed = allAllowed;
	}
}
