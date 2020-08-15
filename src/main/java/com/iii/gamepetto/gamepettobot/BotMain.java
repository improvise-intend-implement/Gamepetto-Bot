package com.iii.gamepetto.gamepettobot;

import com.iii.gamepetto.gamepettobot.event.GuildEvent;
import com.iii.gamepetto.gamepettobot.service.GuildService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;

@QuarkusMain
public class BotMain implements QuarkusApplication {

	@ConfigProperty(name = "gamepetto.bot.token")
	String token;
	@Inject
	GuildEvent guildEvent;
	@Inject
	GuildService guildService;

	@Override
	public int run(String... args) throws Exception {
		JDA jda = JDABuilder.createDefault(this.token).build();
		jda.addEventListener(this.guildEvent);
		this.guildService.loadAllPrefixes();
		jda.awaitReady();
		Quarkus.waitForExit();
		return 0;
	}

}
