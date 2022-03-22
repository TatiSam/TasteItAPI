package com.tatisam.tasteit.repositories.app;

import com.tatisam.tasteit.entities.app.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface for work with table comments {@link Comment} from database
 * @implSpec JpaRepository
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 22/03/22
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByCountryId(long id);
}
