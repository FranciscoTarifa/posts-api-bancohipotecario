package com.bancohogar.postapi.client;

import com.bancohogar.postapi.dto.external.ExternalComment;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestPath;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@RegisterRestClient(configKey = "posts-api-external")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CommentExternalClient {

    @GET
    @Path("/posts/{postId}/comments")
    List<ExternalComment> getCommentsByPostId(@RestPath Long postId);
}