package com.example.munglog.mbti.service;

import com.example.munglog.mbti.domain.MbtiLog;
import com.example.munglog.mbti.dto.MbtiLogDTO;
import com.example.munglog.mbti.repository.MbtiLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class MbtiLogService {

    @Autowired
    private MbtiLogRepository mbtiLogRepository;

    public MbtiLogDTO createMbtiLog(MbtiLogDTO mbtiLogDTO) {
        MbtiLog mbtiLog = new MbtiLog();
        mbtiLog.setUserId(mbtiLogDTO.getUserId());
        mbtiLog.setMbti(mbtiLogDTO.getMbti());
        mbtiLog.setDate(LocalDate.parse(mbtiLogDTO.getDate()));
        mbtiLog.setTimestamp(mbtiLogDTO.getTimestamp());

        mbtiLog = mbtiLogRepository.save(mbtiLog);
        mbtiLogDTO.setId(mbtiLog.getId());
        return mbtiLogDTO;
    }

    public MbtiLogDTO getMbtiLog(Long logId) {
        Optional<MbtiLog> optionalMbtiLog = mbtiLogRepository.findById(logId);
        if (optionalMbtiLog.isPresent()) {
            MbtiLog mbtiLog = optionalMbtiLog.get();
            MbtiLogDTO mbtiLogDTO = new MbtiLogDTO();
            mbtiLogDTO.setId(mbtiLog.getId());
            mbtiLogDTO.setUserId(mbtiLog.getUserId());
            mbtiLogDTO.setMbti(mbtiLog.getMbti());
            mbtiLogDTO.setDate(mbtiLog.getDate().toString());
            mbtiLogDTO.setTimestamp(mbtiLog.getTimestamp());
            return mbtiLogDTO;
        }
        return null;
    }

    public MbtiLogDTO updateMbtiLog(Long logId, MbtiLogDTO mbtiLogDTO) {
        Optional<MbtiLog> optionalMbtiLog = mbtiLogRepository.findById(logId);
        if (optionalMbtiLog.isPresent()) {
            MbtiLog mbtiLog = optionalMbtiLog.get();
            mbtiLog.setUserId(mbtiLogDTO.getUserId());
            mbtiLog.setMbti(mbtiLogDTO.getMbti());
            mbtiLog.setDate(LocalDate.parse(mbtiLogDTO.getDate()));
            mbtiLog.setTimestamp(mbtiLogDTO.getTimestamp());

            mbtiLog = mbtiLogRepository.save(mbtiLog);
            mbtiLogDTO.setId(mbtiLog.getId());
            return mbtiLogDTO;
        }
        return null;
    }

    public void deleteMbtiLog(Long logId) {
        mbtiLogRepository.deleteById(logId);
    }
}
