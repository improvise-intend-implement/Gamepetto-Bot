package com.iii.gamepetto.gamepettobot.client;

import com.iii.gamepetto.gamepettobot.exception.ClientExceptionMapper;
import com.iii.gamepetto.gamepettobot.model.api.request.GuildRequest;
import com.iii.gamepetto.gamepettobot.model.api.response.GuildResponse;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/guild")
@RegisterRestClient(configKey = "gamepetto-guild-api")
@RegisterProvider(value = ClientExceptionMapper.class)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public interface GuildRestClient {

	@POST
	GuildResponse registerGuild(GuildRequest guildRequest);

}
