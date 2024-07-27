package com.example.munglog.User.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userid;
    private String username;
    private String password;
    private String email;
    private String phone;
    private boolean roles;
    private String Nickname;

    @ManyToMany(mappedBy = "users")
    private Set<Family> families = new HashSet<>();
}
