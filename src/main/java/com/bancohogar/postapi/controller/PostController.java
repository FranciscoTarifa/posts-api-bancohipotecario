package com.bancohogar.postapi.controller;

import com.bancohogar.postapi.dto.PostWithDetails;
import com.bancohogar.postapi.service.PostService;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Posts", description = "Endpoints para gestionar posts y sus detalles")
public class PostController {

    @Inject
    PostService postService;

    @GET
    @Operation(summary = "Obtener todos los posts con detalles", description = "Obtiene la lista de posts, incluyendo la información del usuario y los comentarios para cada uno.")
    @APIResponse(responseCode = "200", description = "Lista de posts con detalles obtenida exitosamente.")
    @APIResponse(responseCode = "500", description = "Error interno al procesar la solicitud.")
    public Response getAllPostsWithDetails() {
        try {
            List<PostWithDetails> postsWithDetails = postService.getAllPostsWithDetails();
            return Response.ok(postsWithDetails).build();
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno al obtener los posts con detalles: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Eliminar un post", description = "Realiza una llamada DELETE a la API externa para eliminar un post específico.")
    @APIResponse(responseCode = "204", description = "Post eliminado exitosamente (simulado).")
    @APIResponse(responseCode = "404", description = "Post no encontrado en la API externa.")
    @APIResponse(responseCode = "500", description = "Error interno al procesar la solicitud.")
    public Response deletePost(@PathParam("id") @NotNull Long id) {
        try {
            postService.deletePostById(id);
            return Response.noContent().build();
        } catch (jakarta.ws.rs.NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Post con ID " + id + " no encontrado en la API externa.")
                    .build();
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno al eliminar el post con ID " + id + ": " + e.getMessage())
                    .build();
        }
    }
}
