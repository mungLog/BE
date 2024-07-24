package com.example.munglog.User.Domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "group")
//    private Set<Animal> animals;

    @ManyToMany(mappedBy = "groups")
    private Set<User> users;

}
