package com.example.munglog.user.mbti.repository;

import com.example.munglog.mbti.domain.MbtiLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MbtiLogRepository extends JpaRepository<MbtiLog, Long> {
}
