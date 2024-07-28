package com.example.munglog.user.exercise.repository;

import com.example.munglog.exercise.domain.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {
}
