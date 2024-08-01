package com.example.munglog.community.service;

import com.example.munglog.community.domain.CommunityPost;
import com.example.munglog.community.dto.CommunityPostDTO;

import com.example.munglog.community.repository.CommunityPostRepository;
import com.example.munglog.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommunityPostService {

    @Autowired
    private CommunityPostRepository communityPostRepository;

    @Autowired
    private ImageUtil imageUtil;

    public CommunityPostDTO createPost(CommunityPostDTO postDTO, MultipartFile file) throws IOException {
        CommunityPost post = new CommunityPost();
        post.setUserId(postDTO.getUserId());
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setCategory(postDTO.getCategory());
        post.setTimestamp(LocalDateTime.parse(postDTO.getTimestamp().toString()));

        String imageUrl = imageUtil.saveImage(file);
        post.setImageUrl(imageUrl);

        post = communityPostRepository.save(post);
        postDTO.setId(post.getId());
        postDTO.setImageUrl(imageUrl);
        return postDTO;
    }

    public CommunityPostDTO getPost(Long postId) {
        Optional<CommunityPost> optionalPost = communityPostRepository.findById(postId);
        if (optionalPost.isPresent()) {
            CommunityPost post = optionalPost.get();
            CommunityPostDTO postDTO = new CommunityPostDTO();
            postDTO.setId(post.getId());
            postDTO.setUserId(post.getUserId());
            postDTO.setTitle(post.getTitle());
            postDTO.setContent(post.getContent());
            postDTO.setCategory(post.getCategory());
            postDTO.setTimestamp(LocalDateTime.parse(post.getTimestamp().toString()));
            postDTO.setImageUrl(post.getImageUrl());
            return postDTO;
        }
        return null;
    }

    public CommunityPostDTO updatePost(Long postId, CommunityPostDTO postDTO, MultipartFile file) throws IOException {
        Optional<CommunityPost> optionalPost = communityPostRepository.findById(postId);
        if (optionalPost.isPresent()) {
            CommunityPost post = optionalPost.get();
            post.setUserId(postDTO.getUserId());
            post.setTitle(postDTO.getTitle());
            post.setContent(postDTO.getContent());
            post.setCategory(postDTO.getCategory());
            post.setTimestamp(LocalDateTime.parse(postDTO.getTimestamp().toString()));

            String imageUrl = imageUtil.saveImage(file);
            post.setImageUrl(imageUrl);

            post = communityPostRepository.save(post);
            postDTO.setId(post.getId());
            postDTO.setImageUrl(imageUrl);
            return postDTO;
        }
        return null;
    }

    public void deletePost(Long postId) {
        communityPostRepository.deleteById(postId);
    }


    public List<CommunityPostDTO> getAllPosts() {
        List<CommunityPost> posts = communityPostRepository.findAll();
        return posts.stream().map(post -> {
            CommunityPostDTO postDTO = new CommunityPostDTO();
            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());
            postDTO.setContent(post.getContent());
            postDTO.setCategory(post.getCategory());
            postDTO.setTimestamp(post.getTimestamp());
            postDTO.setImageUrl(post.getImageUrl());
            return postDTO;
        }).collect(Collectors.toList());
    }
}
