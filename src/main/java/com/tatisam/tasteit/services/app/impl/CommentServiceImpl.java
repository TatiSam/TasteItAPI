package com.tatisam.tasteit.services.app.impl;

import com.tatisam.tasteit.entities.app.Comment;
import com.tatisam.tasteit.entities.app.Country;
import com.tatisam.tasteit.exceptions.app.ResourceNotFoundException;
import com.tatisam.tasteit.payload.app.CommentDTO;
import com.tatisam.tasteit.repositories.app.CommentRepository;
import com.tatisam.tasteit.repositories.app.CountryRepository;
import com.tatisam.tasteit.services.app.CommentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Comment Service is used to work with {@link Comment} from database
 * @implSpec {@link CommentService}
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 22/03/22
 */
@Service
public class CommentServiceImpl implements CommentService {
    private final CountryRepository countryRepository;
    private final CommentRepository commentRepository;
    private final TypeMap<Comment, CommentDTO> toDto;
    private final TypeMap<CommentDTO, Comment> toComment;

    public CommentServiceImpl(CountryRepository countryRepository,
                              CommentRepository commentRepository,
                              ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.commentRepository = commentRepository;
        toDto = modelMapper.createTypeMap(Comment.class, CommentDTO.class);
        toComment = modelMapper.createTypeMap(CommentDTO.class, Comment.class);
    }

    /**
     * Create {@link Comment} in database
     * @param countryId {@link Country} id
     * @param dto {@link CommentDTO}
     * @return {@link CommentDTO} from created {@link Comment}
     * @throws {@link ResourceNotFoundException} when country with countryId not exists in database
     * @since 22/03/22
     */
    @Override
    public CommentDTO createComment(long countryId, CommentDTO dto) {
        var country = countryRepository.findById(countryId).orElseThrow(
                () -> new ResourceNotFoundException("Country", "id", countryId)
        );
        Comment comment = toComment.map(dto);
        comment.setCountry(country);
        Comment saved = commentRepository.save(comment);
        return toDto.map(saved);
    }

    /**
     * Get List of {@link Comment} by {@link Country} id from database
     * @param countryId {@link Country} id
     * @return List of {@link Comment}
     * @since 22/03/22
     */
    @Override
    public List<CommentDTO> getCommentsByCountryId(long countryId) {
        List<Comment> comments = commentRepository.findCommentsByCountryIdOrderByDateDesc(countryId);
        return comments.stream().map(toDto::map).collect(Collectors.toList());
    }

    /**
     * Update {@link Comment} in database by id
     * @param commentId {@link Comment} id
     * @param dto {@link CommentDTO}
     * @return {@link CommentDTO} from updated Comment
     * @throws {@link ResourceNotFoundException} when comment with commentId not exists in database
     * @since 22/03/22
     */
    @Override
    public CommentDTO updateCommentById(long commentId, CommentDTO dto) {
        Comment beforeEdit = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        Comment edited = toComment.map(dto);
        edited.setId(beforeEdit.getId());
        edited.setCountry(beforeEdit.getCountry());
        edited.setDate(beforeEdit.getDate());
        Comment saved = commentRepository.save(edited);
        return toDto.map(saved);
    }

    /**
     * Delete {@link Comment} from database by id
     * @param commentId {@link Comment} id
     * @return deleted {@link CommentDTO}
     * @throws {@link ResourceNotFoundException} when comment with commentId not exists in database
     * @since 22/03/22
     */
    @Override
    public CommentDTO deleteCommentById(long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        commentRepository.delete(comment);
        return toDto.map(comment);
    }
}
