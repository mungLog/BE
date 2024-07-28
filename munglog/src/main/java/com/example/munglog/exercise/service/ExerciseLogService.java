package com.example.munglog.user.exercise.service;

import com.example.munglog.exercise.domain.ExerciseLog;
import com.example.munglog.exercise.dto.ExerciseLogDTO;
import com.example.munglog.user.exercise.repository.ExerciseLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ExerciseLogService {

    @Autowired
    private ExerciseLogRepository exerciseLogRepository;

    public ExerciseLogDTO createExerciseLog(ExerciseLogDTO exerciseLogDTO) {
        ExerciseLog exerciseLog = new ExerciseLog();
        exerciseLog.setPetId(exerciseLogDTO.getPetId());
        exerciseLog.setDate(LocalDate.parse(exerciseLogDTO.getDate()));
        exerciseLog.setDuration(exerciseLogDTO.getDuration());
        exerciseLog.setType(exerciseLogDTO.getType());
        exerciseLog.setDistance(exerciseLogDTO.getDistance());
        exerciseLog.setTimestamp(exerciseLogDTO.getTimestamp());

        exerciseLog = exerciseLogRepository.save(exerciseLog);
        exerciseLogDTO.setId(exerciseLog.getId());
        return exerciseLogDTO;
    }

    public ExerciseLogDTO getExerciseLog(Long logId) {
        Optional<ExerciseLog> optionalExerciseLog = exerciseLogRepository.findById(logId);
        if (optionalExerciseLog.isPresent()) {
            ExerciseLog exerciseLog = optionalExerciseLog.get();
            ExerciseLogDTO exerciseLogDTO = new ExerciseLogDTO();
            exerciseLogDTO.setId(exerciseLog.getId());
            exerciseLogDTO.setPetId(exerciseLog.getPetId());
            exerciseLogDTO.setDate(exerciseLog.getDate().toString());
            exerciseLogDTO.setDuration(exerciseLog.getDuration());
            exerciseLogDTO.setType(exerciseLog.getType());
            exerciseLogDTO.setDistance(exerciseLog.getDistance());
            exerciseLogDTO.setTimestamp(exerciseLog.getTimestamp());
            return exerciseLogDTO;
        }
        return null;
    }

    public ExerciseLogDTO updateExerciseLog(Long logId, ExerciseLogDTO exerciseLogDTO) {
        Optional<ExerciseLog> optionalExerciseLog = exerciseLogRepository.findById(logId);
        if (optionalExerciseLog.isPresent()) {
            ExerciseLog exerciseLog = optionalExerciseLog.get();
            exerciseLog.setPetId(exerciseLogDTO.getPetId());
            exerciseLog.setDate(LocalDate.parse(exerciseLogDTO.getDate()));
            exerciseLog.setDuration(exerciseLogDTO.getDuration());
            exerciseLog.setType(exerciseLogDTO.getType());
            exerciseLog.setDistance(exerciseLogDTO.getDistance());
            exerciseLog.setTimestamp(exerciseLogDTO.getTimestamp());

            exerciseLog = exerciseLogRepository.save(exerciseLog);
            exerciseLogDTO.setId(exerciseLog.getId());
            return exerciseLogDTO;
        }
        return null;
    }

    public void deleteExerciseLog(Long logId) {
        exerciseLogRepository.deleteById(logId);
    }
}
