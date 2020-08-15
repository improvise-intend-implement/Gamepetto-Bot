package com.iii.gamepetto.gamepettobot.client;

import com.iii.gamepetto.gamepettobot.exception.ClientExceptionMapper;
import com.iii.gamepetto.gamepettobot.model.api.request.GuildRequest;
import com.iii.gamepetto.gamepettobot.model.api.response.GuildResponse;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("/guild")
@RegisterRestClient(configKey = "gamepetto-guild-api")
@RegisterProvider(value = ClientExceptionMapper.class)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public interface GuildRestClient {

	@POST
	GuildResponse registerGuild(GuildRequest guildRequest);

	@DELETE
	@Path("/{guildId}")
	String deleteGuild(@PathParam("guildId") String guildId);

	@GET
	@Path("/prefix")
	Map<String, String> getAllPrefixes();
}
