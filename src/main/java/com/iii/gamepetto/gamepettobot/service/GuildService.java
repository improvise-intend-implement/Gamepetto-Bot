package com.iii.gamepetto.gamepettobot.service;

import com.iii.gamepetto.gamepettobot.model.api.response.GuildResponse;
import net.dv8tion.jda.api.entities.Guild;

public interface GuildService {

	GuildResponse registerGuild(Guild guild);

}
