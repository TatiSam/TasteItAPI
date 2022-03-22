package com.tatisam.tasteit.controllers.app;

import com.tatisam.tasteit.payload.app.CommentDTO;
import com.tatisam.tasteit.services.app.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * RestController for work with {@link CommentService}
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 22/03/22
 */
@RestController
@RequestMapping("/api/1")
@CrossOrigin(origins = {"http://localhost:8080"})
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Create valid {@link com.tatisam.tasteit.entities.app.Comment}
     * @param countryId {@link com.tatisam.tasteit.entities.app.Country} id
     * @param dto {@link CommentDTO}
     * @return {@link ResponseEntity} with status Created and {@link CommentDTO}
     * @since 22/03/22
     */
    @PostMapping("/countries/{id}/comments")
    public ResponseEntity<CommentDTO> createComment(
            @PathVariable("id") long countryId,
            @Valid @RequestBody() CommentDTO dto) {
        CommentDTO comment = commentService.createComment(countryId, dto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    /**
     * Get List of {@link com.tatisam.tasteit.entities.app.Comment} by {@link com.tatisam.tasteit.entities.app.Country} id
     * @param countryId {@link com.tatisam.tasteit.entities.app.Country} id
     * @return {@link ResponseEntity} with status Ok and List of {@link CommentDTO}
     * @since 22/03/22
     */
    @GetMapping("/countries/{id}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByCountryId(
            @PathVariable("id") long countryId) {
        return ResponseEntity.ok().body(commentService.getCommentsByCountryId(countryId));
    }

    /**
     * Update {@link com.tatisam.tasteit.entities.app.Comment} in database by id
     * @param id {@link com.tatisam.tasteit.entities.app.Comment} id
     * @param dto {@link CommentDTO}
     * @return {@link ResponseEntity} with status Ok and updated {@link CommentDTO}
     * @since 22/03/22
     */
    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentDTO> updateCommentById(
            @PathVariable("id") long id,
            @Valid @RequestBody CommentDTO dto) {
        return ResponseEntity.ok().body(commentService.updateCommentById(id, dto));
    }

    /**
     * Delete {@link com.tatisam.tasteit.entities.app.Comment} from database by id
     * @param id {@link com.tatisam.tasteit.entities.app.Comment} id
     * @return {@link ResponseEntity} with status Ok and {@link CommentDTO}
     * @since 22/03/22
     */
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<CommentDTO> deleteCommentById(
            @PathVariable("id") long id) {
        return ResponseEntity.ok().body(commentService.deleteCommentById(id));
    }
}
