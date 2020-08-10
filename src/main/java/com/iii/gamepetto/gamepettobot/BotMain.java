package com.iii.gamepetto.gamepettobot;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

@QuarkusMain
public class BotMain implements QuarkusApplication {

	@Override
	public int run(String... args) throws Exception {
		JDA jda = JDABuilder.createDefault("NzM2NTQ0MzQ1NTA2NzA5NTQ2.XxwWYA.3C4Ltg-owpYAXvJhvjTwUhKQXW4").build();
		jda.addEventListener();
		jda.awaitReady();
		Quarkus.waitForExit();
		return 0;
	}

}
