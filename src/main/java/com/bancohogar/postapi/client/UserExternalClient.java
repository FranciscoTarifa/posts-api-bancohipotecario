package com.bancohogar.postapi.client;

import com.bancohogar.postapi.dto.external.ExternalUser;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestPath;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "posts-api-external")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface UserExternalClient {

    @GET
    @Path("/users/{userId}")
    ExternalUser getUserById(@RestPath Long userId);
}
