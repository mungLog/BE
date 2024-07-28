package com.example.munglog.user.carelog.repository;

import com.example.munglog.carelog.domain.CareLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareLogRepository extends JpaRepository<CareLog, Long> {
}
