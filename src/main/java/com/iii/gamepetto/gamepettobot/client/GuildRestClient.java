package com.iii.gamepetto.gamepettobot.client;

import com.iii.gamepetto.gamepettobot.model.api.request.GuildRequest;
import com.iii.gamepetto.gamepettobot.model.api.response.GuildResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/guild")
@RegisterRestClient(configKey = "gamepetto-guild-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public interface GuildRestClient {

	@POST
	GuildResponse registerGuild(@BeanParam GuildRequest guildRequest);

}
