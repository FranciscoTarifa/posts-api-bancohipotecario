package com.bancohogar.postapi.service;

import com.bancohogar.postapi.dto.PostWithDetails;

import java.util.List;

public interface PostService {

    /**
     * Obtiene todos los posts con sus detalles (usuario y comentarios)
     * @return Lista de posts con detalles
     */
    List<PostWithDetails> getAllPostsWithDetails();

    /**
     * Elimina un post por su ID
     * @param id ID del post a eliminar
     */
    void deletePostById(Long id);
}