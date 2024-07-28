package com.example.munglog.user.community.controller;

import com.example.munglog.community.dto.CommentDTO;
import com.example.munglog.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{post_id}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long post_id, @RequestBody CommentDTO commentDTO) {
        commentDTO.setPostId(post_id);
        return ResponseEntity.ok(commentService.createComment(commentDTO));
    }

    @GetMapping("/{comment_id}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable Long comment_id) {
        return ResponseEntity.ok(commentService.getComment(comment_id));
    }

    @PostMapping("/update/{comment_id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long comment_id, @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.updateComment(comment_id, commentDTO));
    }

    @DeleteMapping("/delete/{comment_id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long comment_id) {
        commentService.deleteComment(comment_id);
        return ResponseEntity.ok().build();
    }
}
