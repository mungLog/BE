package com.example.munglog.community.repository;

import com.example.munglog.community.domain.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {
}
