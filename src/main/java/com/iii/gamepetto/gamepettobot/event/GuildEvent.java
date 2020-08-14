package com.iii.gamepetto.gamepettobot.event;

import com.iii.gamepetto.gamepettobot.service.GuildService;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@Dependent
public class GuildEvent extends ListenerAdapter {

	private final static Logger log = Logger.getLogger(GuildEvent.class);
	@Inject
	GuildService guildService;

	@Override
	public void onGuildJoin(GuildJoinEvent event) {
		this.guildService.registerGuild(event.getGuild());
	}

	@Override
	public void onGuildLeave(GuildLeaveEvent event) {
		try {
			this.guildService.deleteGuild(event.getGuild().getId());
		} catch (NotFoundException ex) {
			log.info(ex.getMessage());
		}
	}

}
