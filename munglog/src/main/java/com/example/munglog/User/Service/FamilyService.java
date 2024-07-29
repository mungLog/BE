package com.example.munglog.User.Service;

import com.example.munglog.User.Domain.Family;
import com.example.munglog.User.Repository.FamilyRepository;
import com.example.munglog.pet.domain.Pet;
import com.example.munglog.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
    public class FamilyService {

        @Autowired
        private FamilyRepository familyRepository;

        @Autowired
        private PetRepository animalRepository;

        public Pet addAnimalToFamily(Long familyId, Pet animal) {
            return familyRepository.findById(familyId).map(family -> {
                animal.setFamily(family);
                return animalRepository.save(animal);
            }).orElse(null);
        }

        public Set<Pet> getFamilyAnimals(Long familyId) {
            return familyRepository.findById(familyId)
                    .map(Family::getAnimals)
                    .orElse(null);
        }
}

