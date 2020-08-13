package com.iii.gamepetto.gamepettobot;

import com.iii.gamepetto.gamepettobot.event.GuildEvent;
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

	@Override
	public int run(String... args) throws Exception {
		JDA jda = JDABuilder.createDefault(token).build();
		jda.addEventListener(guildEvent);
		jda.awaitReady();
		Quarkus.waitForExit();
		return 0;
	}

}
