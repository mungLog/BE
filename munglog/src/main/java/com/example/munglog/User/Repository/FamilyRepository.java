package com.example.munglog.User.Repository;

import com.example.munglog.User.Domain.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Long> {
}
