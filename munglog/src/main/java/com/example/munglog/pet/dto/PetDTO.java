package com.example.munglog.pet.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PetDTO {

    private Long id;
    private Long userId;
    private String name;
    private String breed;
    private int age;
    private double weight;
    private String date;
    private LocalDateTime timestamp;
    private String imageUrl;
    private boolean neutered;
    private String gender;  // 성별 추가
    private double dailyKcal;


}
