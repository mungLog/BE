package com.example.munglog.User.Repository;

import com.example.munglog.User.Domain.Family;
import com.example.munglog.User.Domain.FamilyRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyRequestRepository extends JpaRepository<FamilyRequest, Long> {
    List<FamilyRequest> findByFamilyAndProcessed(Family family, boolean processed);
}