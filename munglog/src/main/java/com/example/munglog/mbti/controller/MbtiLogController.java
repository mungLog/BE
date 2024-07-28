package com.example.munglog.user.mbti.controller;

import com.example.munglog.mbti.dto.MbtiLogDTO;
import com.example.munglog.mbti.service.MbtiLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mbti")
public class MbtiLogController {

    @Autowired
    private MbtiLogService mbtiLogService;

    @PostMapping
    public ResponseEntity<MbtiLogDTO> createMbtiLog(@RequestBody MbtiLogDTO mbtiLogDTO) {
        return ResponseEntity.ok(mbtiLogService.createMbtiLog(mbtiLogDTO));
    }

    @GetMapping("/{log_id}")
    public ResponseEntity<MbtiLogDTO> getMbtiLog(@PathVariable Long log_id) {
        return ResponseEntity.ok(mbtiLogService.getMbtiLog(log_id));
    }

    @PostMapping("/update/{log_id}")
    public ResponseEntity<MbtiLogDTO> updateMbtiLog(@PathVariable Long log_id, @RequestBody MbtiLogDTO mbtiLogDTO) {
        return ResponseEntity.ok(mbtiLogService.updateMbtiLog(log_id, mbtiLogDTO));
    }

    @DeleteMapping("/delete/{log_id}")
    public ResponseEntity<Void> deleteMbtiLog(@PathVariable Long log_id) {
        mbtiLogService.deleteMbtiLog(log_id);
        return ResponseEntity.ok().build();
    }
}
