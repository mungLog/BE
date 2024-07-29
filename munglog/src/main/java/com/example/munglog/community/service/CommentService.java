package com.example.munglog.community.service;

import com.example.munglog.community.domain.Comment;
import com.example.munglog.community.dto.CommentDTO;
import com.example.munglog.community.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;



    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setPostId(commentDTO.getPostId());
        comment.setUserId(commentDTO.getUserId());
        comment.setContent(commentDTO.getContent());
        comment.setTimestamp(LocalDateTime.parse(comment.getTimestamp().toString()));


        comment = commentRepository.save(comment);
        commentDTO.setId(comment.getId());
        return commentDTO;
    }

    public CommentDTO getComment(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setPostId(comment.getPostId());
            commentDTO.setUserId(comment.getUserId());
            commentDTO.setContent(comment.getContent());
            commentDTO.setTimestamp(LocalDateTime.parse(comment.getTimestamp().toString()));
            return commentDTO;
        }
        return null;
    }

    public CommentDTO updateComment(Long commentId, CommentDTO commentDTO) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setPostId(commentDTO.getPostId());
            comment.setUserId(commentDTO.getUserId());
            comment.setContent(commentDTO.getContent());
            comment.setTimestamp(LocalDateTime.parse(comment.getTimestamp().toString()));

            comment = commentRepository.save(comment);
            commentDTO.setId(comment.getId());
            return commentDTO;
        }
        return null;
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
