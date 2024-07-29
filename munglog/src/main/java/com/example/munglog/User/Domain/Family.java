package com.example.munglog.User.Domain;

import com.example.munglog.pet.domain.Pet;
import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "user_family",
            joinColumns = @JoinColumn(name = "family_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private Set<Pet> animals = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;



}
