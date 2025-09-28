package com.bancohogar.postapi.client;

import com.bancohogar.postapi.dto.external.ExternalPost;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestPath;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@RegisterRestClient(configKey = "posts-api-external")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface PostExternalClient {

    @GET
    @Path("/posts")
    List<ExternalPost> getAllPosts();

    @GET
    @Path("/posts/{id}")
    ExternalPost getPostById(@RestPath Long id);

    @DELETE
    @Path("/posts/{id}")
    void deletePostById(@RestPath Long id);
}