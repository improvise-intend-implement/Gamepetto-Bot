package com.iii.gamepetto.gamepettobot;

import com.iii.gamepetto.gamepettobot.event.GuildEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;

@ApplicationScoped
public class JdaProducer {

	private static final Logger log = LogManager.getLogger(JdaProducer.class);
	@ConfigProperty(name = "gamepetto.bot.token")
	String discordToken;
	@Inject
	GuildEvent guildEvent;

	@Produces
	@ApplicationScoped
	JDA produceJda() {
		try {
			JDA jda = JDABuilder.createDefault(this.discordToken).build();
			jda.addEventListener(this.guildEvent);
			return jda.awaitReady();
		} catch (InterruptedException | LoginException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	void disposeJda(@Disposes JDA jda) {
		jda.shutdown();
	}
}
