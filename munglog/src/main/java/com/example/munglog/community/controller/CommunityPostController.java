package com.example.munglog.community.controller;


import com.example.munglog.community.dto.CommunityPostDTO;
import com.example.munglog.community.service.CommunityPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/posts")
public class CommunityPostController {

    @Autowired
    private CommunityPostService communityPostService;

    @PostMapping
    public ResponseEntity<CommunityPostDTO> createPost(@RequestPart("post") CommunityPostDTO postDTO, @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(communityPostService.createPost(postDTO, file));
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<CommunityPostDTO> getPost(@PathVariable Long post_id) {
        return ResponseEntity.ok(communityPostService.getPost(post_id));
    }

    @PostMapping("/update/{post_id}")
    public ResponseEntity<CommunityPostDTO> updatePost(@PathVariable Long post_id, @RequestPart("post") CommunityPostDTO postDTO, @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(communityPostService.updatePost(post_id, postDTO, file));
    }

    @DeleteMapping("/delete/{post_id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long post_id) {
        communityPostService.deletePost(post_id);
        return ResponseEntity.ok().build();
    }
}
