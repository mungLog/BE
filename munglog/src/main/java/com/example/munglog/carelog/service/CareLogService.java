package com.example.munglog.carelog.service;

import com.example.munglog.carelog.domain.CareLog;
import com.example.munglog.carelog.dto.CareLogDTO;
import com.example.munglog.carelog.repository.CareLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CareLogService {

    @Autowired
    private CareLogRepository careLogRepository;

    public CareLogDTO createCareLog(CareLogDTO careLogDTO) {
        CareLog careLog = new CareLog();
        careLog.setPetId(careLogDTO.getPetId());
        careLog.setUserId(careLogDTO.getUserId());
        careLog.setType(careLogDTO.getType());
        careLog.setDescription(careLogDTO.getDescription());
        careLog.setDate(LocalDateTime.parse(careLogDTO.getDate()));
        careLog.setTimestamp(careLogDTO.getTimestamp());

        careLog = careLogRepository.save(careLog);
        careLogDTO.setId(careLog.getId());
        return careLogDTO;
    }

    public CareLogDTO getCareLog(Long logId) {
        Optional<CareLog> optionalCareLog = careLogRepository.findById(logId);
        if (optionalCareLog.isPresent()) {
            CareLog careLog = optionalCareLog.get();
            CareLogDTO careLogDTO = new CareLogDTO();
            careLogDTO.setId(careLog.getId());
            careLogDTO.setPetId(careLog.getPetId());
            careLogDTO.setUserId(careLog.getUserId());
            careLogDTO.setType(careLog.getType());
            careLogDTO.setDescription(careLog.getDescription());
            careLogDTO.setDate(careLog.getDate().toString());
            careLogDTO.setTimestamp(careLog.getTimestamp());
            return careLogDTO;
        }
        return null;
    }

    public CareLogDTO updateCareLog(Long logId, CareLogDTO careLogDTO) {
        Optional<CareLog> optionalCareLog = careLogRepository.findById(logId);
        if (optionalCareLog.isPresent()) {
            CareLog careLog = optionalCareLog.get();
            careLog.setPetId(careLogDTO.getPetId());
            careLog.setUserId(careLogDTO.getUserId());
            careLog.setType(careLogDTO.getType());
            careLog.setDescription(careLogDTO.getDescription());
            careLog.setDate(LocalDateTime.parse(careLogDTO.getDate()));
            careLog.setTimestamp(careLogDTO.getTimestamp());

            careLog = careLogRepository.save(careLog);
            careLogDTO.setId(careLog.getId());
            return careLogDTO;
        }
        return null;
    }

    public void deleteCareLog(Long logId) {
        careLogRepository.deleteById(logId);
    }
}
