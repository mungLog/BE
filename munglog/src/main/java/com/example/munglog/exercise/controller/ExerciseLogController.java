package com.example.munglog.exercise.controller;


import com.example.munglog.exercise.dto.ExerciseLogDTO;
import com.example.munglog.exercise.service.ExerciseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercise")
public class ExerciseLogController {

    @Autowired
    private ExerciseLogService exerciseLogService;

    @PostMapping
    public ResponseEntity<ExerciseLogDTO> createExerciseLog(@RequestBody ExerciseLogDTO exerciseLogDTO) {
        return ResponseEntity.ok(exerciseLogService.createExerciseLog(exerciseLogDTO));
    }

    @GetMapping("/{log_id}")
    public ResponseEntity<ExerciseLogDTO> getExerciseLog(@PathVariable Long log_id) {
        return ResponseEntity.ok(exerciseLogService.getExerciseLog(log_id));
    }

    @PostMapping("/update/{log_id}")
    public ResponseEntity<ExerciseLogDTO> updateExerciseLog(@PathVariable Long log_id, @RequestBody ExerciseLogDTO exerciseLogDTO) {
        return ResponseEntity.ok(exerciseLogService.updateExerciseLog(log_id, exerciseLogDTO));
    }

    @DeleteMapping("/delete/{log_id}")
    public ResponseEntity<Void> deleteExerciseLog(@PathVariable Long log_id) {
        exerciseLogService.deleteExerciseLog(log_id);
        return ResponseEntity.ok().build();
    }
}
