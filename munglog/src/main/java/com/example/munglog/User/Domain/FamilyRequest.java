package com.example.munglog.User.Domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class FamilyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private User applicant;

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    private LocalDateTime requestDate;
    private boolean approved;
    private boolean processed;
}
