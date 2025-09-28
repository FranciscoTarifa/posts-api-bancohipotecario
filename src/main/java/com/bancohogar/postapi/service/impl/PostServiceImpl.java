package com.bancohogar.postapi.service.impl;

import com.bancohogar.postapi.client.CommentExternalClient;
import com.bancohogar.postapi.client.PostExternalClient;
import com.bancohogar.postapi.client.UserExternalClient;
import com.bancohogar.postapi.dto.PostWithDetails;
import com.bancohogar.postapi.dto.external.ExternalComment;
import com.bancohogar.postapi.dto.external.ExternalPost;
import com.bancohogar.postapi.dto.external.ExternalUser;
import com.bancohogar.postapi.service.PostService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@ApplicationScoped
public class PostServiceImpl implements PostService {

    private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    @Inject
    @RestClient
    PostExternalClient postExternalClient;

    @Inject
    @RestClient
    CommentExternalClient commentExternalClient;

    @Inject
    @RestClient
    UserExternalClient userExternalClient;

    @Inject
    Executor executor; // Inyecta un executor de Quarkus para resolver problemas de classloader

    @Override
    public List<PostWithDetails> getAllPostsWithDetails() {
        log.info("Fetching all posts with details...");
        try {
            List<ExternalPost> externalPosts = postExternalClient.getAllPosts();
            log.debug("Fetched {} posts from external API", externalPosts.size());

            List<CompletableFuture<PostWithDetails>> futures = externalPosts.stream()
                    .map(post -> CompletableFuture.supplyAsync(() -> {
                        try {
                            List<ExternalComment> comments = commentExternalClient.getCommentsByPostId(post.getId());
                            log.debug("Fetched {} comments for post ID: {}", comments.size(), post.getId());

                            ExternalUser user = userExternalClient.getUserById(post.getUserId());
                            log.debug("Fetched user for post ID: {} with user ID: {}", post.getId(), user.getId());

                            return new PostWithDetails(post, user, comments);
                        } catch (Exception e) {
                            log.error("Error processing post ID: {}", post.getId(), e);
                            throw e;
                        }
                    }, executor)) // <-- Aquí usamos el executor inyectado para evitar problemas de classloader
                    .collect(Collectors.toList());

            List<PostWithDetails> result = futures.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList());

            log.info("Successfully fetched and merged details for {} posts.", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching posts with details", e);
            throw e;
        }
    }

    @Override
    public void deletePostById(Long id) {
        log.info("Deleting post with ID: {}", id);
        try {
            postExternalClient.deletePostById(id);
            log.info("Successfully deleted post with ID: {}", id);
        } catch (Exception e) {
            log.error("Error deleting post with ID: {}", id, e);
            throw e;
        }
    }
}