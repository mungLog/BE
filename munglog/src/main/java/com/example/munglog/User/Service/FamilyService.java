package com.example.munglog.User.Service;

import com.example.munglog.User.Domain.Family;
import com.example.munglog.User.Repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    @Service
    public class FamilyService {

        @Autowired
        private FamilyRepository familyRepository;

//        @Autowired
//        private AnimalRepository animalRepository;
//
//        public Animal addAnimalToFamily(Long familyId, Animal animal) {
//            return familyRepository.findById(familyId).map(family -> {
//                animal.setFamily(family);
//                return animalRepository.save(animal);
//            }).orElse(null);
//        }
//
//        public Set<Animal> getFamilyAnimals(Long familyId) {
//            return familyRepository.findById(familyId)
//                    .map(Family::getAnimals)
//                    .orElse(null);
//        }
}

