package com.iii.gamepetto.gamepettobot.model.api.request;

import java.io.Serializable;

public class GuildRequest implements Serializable {

	private static final long serialVersionUID = -6750706077614545333L;
	private String id;
	private String name;
	private String icon;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
