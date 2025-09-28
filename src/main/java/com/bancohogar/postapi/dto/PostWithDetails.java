package com.bancohogar.postapi.dto;

import com.bancohogar.postapi.dto.external.ExternalComment;
import com.bancohogar.postapi.dto.external.ExternalPost;
import com.bancohogar.postapi.dto.external.ExternalUser;

import java.util.List;

public class PostWithDetails {

    private ExternalPost post;
    private ExternalUser user;
    private List<ExternalComment> comments;

    public PostWithDetails() {
        // Constructor vacío necesario para frameworks como Jackson
    }

    public PostWithDetails(ExternalPost post, ExternalUser user, List<ExternalComment> comments) {
        this.post = post;
        this.user = user;
        this.comments = comments;
    }

    public ExternalPost getPost() {
        return post;
    }

    public void setPost(ExternalPost post) {
        this.post = post;
    }

    public ExternalUser getUser() {
        return user;
    }

    public void setUser(ExternalUser user) {
        this.user = user;
    }

    public List<ExternalComment> getComments() {
        return comments;
    }

    public void setComments(List<ExternalComment> comments) {
        this.comments = comments;
    }
}