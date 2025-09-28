package com.bancohogar.postapi;

import com.bancohogar.postapi.dto.PostWithDetails;
import com.bancohogar.postapi.dto.external.ExternalComment;
import com.bancohogar.postapi.dto.external.ExternalPost;
import com.bancohogar.postapi.dto.external.ExternalUser;
import com.bancohogar.postapi.service.PostService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.InjectMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class PostResourceTest {

    @InjectMock
    PostService postService;

    @Test
    public void testGetAllPostsWithDetails() {
        // Given
        ExternalPost post = new ExternalPost(1L, 1L, "Test Title", "Test Body");
        ExternalUser user = new ExternalUser(1L, "Test User", "testuser", "test@example.com");
        ExternalComment comment = new ExternalComment(1L, 1L, "Test Comment", "comment@example.com", "Comment body");

        PostWithDetails postWithDetails = new PostWithDetails(post, user, Arrays.asList(comment));
        List<PostWithDetails> mockResult = Arrays.asList(postWithDetails);

        Mockito.when(postService.getAllPostsWithDetails()).thenReturn(mockResult);

        // When & Then
        given()
                .when().get("/posts")
                .then()
                .statusCode(200)
                .body("$.size()", is(1))
                .body("[0].post.title", is("Test Title"))
                .body("[0].user.name", is("Test User"))
                .body("[0].comments[0].body", is("Comment body"));
    }

    @Test
    public void testDeletePost() {
        // Given
        Long postId = 1L;

        // When & Then
        given()
                .when().delete("/posts/" + postId)
                .then()
                .statusCode(204);

        Mockito.verify(postService, Mockito.times(1)).deletePostById(postId);
    }
}