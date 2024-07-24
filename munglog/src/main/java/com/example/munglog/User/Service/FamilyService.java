package com.example.munglog.User.Service;

import com.example.munglog.User.Domain.Family;
import com.example.munglog.User.Repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyService {
    @Autowired
    private FamilyRepository familyRepository;

    public Family findGroupById(Long id) {
        return familyRepository.findById(id).orElse(null);
    }

    // Other CRUD methods
}

