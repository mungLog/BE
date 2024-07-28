package com.example.munglog.carelog.controller;

import com.example.munglog.carelog.dto.CareLogDTO;
import com.example.munglog.carelog.service.CareLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carelog")
public class CareLogController {

    @Autowired
    private CareLogService careLogService;

    @PostMapping
    public ResponseEntity<CareLogDTO> createCareLog(@RequestBody CareLogDTO careLogDTO) {
        return ResponseEntity.ok(careLogService.createCareLog(careLogDTO));
    }

    @GetMapping("/{log_id}")
    public ResponseEntity<CareLogDTO> getCareLog(@PathVariable Long log_id) {
        return ResponseEntity.ok(careLogService.getCareLog(log_id));
    }

    @PostMapping("/update/{log_id}")
    public ResponseEntity<CareLogDTO> updateCareLog(@PathVariable Long log_id, @RequestBody CareLogDTO careLogDTO) {
        return ResponseEntity.ok(careLogService.updateCareLog(log_id, careLogDTO));
    }

    @DeleteMapping("/delete/{log_id}")
    public ResponseEntity<Void> deleteCareLog(@PathVariable Long log_id) {
        careLogService.deleteCareLog(log_id);
        return ResponseEntity.ok().build();
    }
}
