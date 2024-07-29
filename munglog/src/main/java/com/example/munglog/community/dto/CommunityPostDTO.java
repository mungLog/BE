package com.example.munglog.community.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class CommunityPostDTO {

    private Long id;
    private Long userId;
    private String title;
    private String content;
    private String category;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timestamp;
    private String imageUrl;


}
