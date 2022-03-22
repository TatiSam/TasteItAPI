package com.tatisam.tasteit.services.app;

import com.tatisam.tasteit.payload.app.CommentDTO;

import java.util.List;

/**
 * Interface for implementing by Comment Service
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 22/03/22
 */
public interface CommentService {
    CommentDTO createComment(long countryId, CommentDTO dto);
    List<CommentDTO> getCommentsByCountryId(long countryId);
    CommentDTO updateCommentById(long id, CommentDTO dto);
    CommentDTO deleteCommentById(long id);
}
