package com.example.munglog.carelog.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class CareLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long petId;
    private Long userId; // ownerId를 userId로 변경
    private String type;
    private String description;
    private LocalDateTime date;
    private LocalDateTime timestamp;


}
