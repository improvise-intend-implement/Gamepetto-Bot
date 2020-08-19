package com.iii.gamepetto.gamepettobot;

import com.iii.gamepetto.gamepettobot.service.GuildService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.inject.Inject;

@QuarkusMain
public class BotMain implements QuarkusApplication {

	@Inject
	GuildService guildService;

	@Override
	public int run(String... args) throws Exception {
		this.guildService.loadAllPrefixes();
		Quarkus.waitForExit();
		return 0;
	}

}
