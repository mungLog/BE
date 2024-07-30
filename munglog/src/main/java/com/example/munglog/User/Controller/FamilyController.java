package com.example.munglog.User.Controller;

import com.example.munglog.User.Domain.Family;
import com.example.munglog.User.Domain.FamilyRequest;
import com.example.munglog.User.Service.FamilyService;
import com.example.munglog.User.Service.UserService;
import com.example.munglog.pet.domain.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/family")
public class FamilyController {
        @Autowired
        private FamilyService familyService;

        @Autowired
        private UserService userService;

        @GetMapping("/{familyId}/requests")
        public List<FamilyRequest> getPendingRequests(@PathVariable Long familyId) {
                return userService.getPendingRequests(familyId);
        }

        @PostMapping("/requests/{requestId}/process")
        public boolean processFamilyRequest(@PathVariable Long requestId, @RequestParam boolean approved) {
                return userService.processFamilyRequest(requestId, approved);
        }

        @GetMapping("/{familyId}")
        public ResponseEntity<Family> getFamilyById(@PathVariable Long familyId) {
                Optional<Family> family = familyService.getFamilyById(familyId);
                return family.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PostMapping("/{familyId}/animals")
        public Pet addAnimalToFamily(@PathVariable Long familyId, @RequestBody Pet animal) {
            return familyService.addAnimalToFamily(familyId, animal);
        }

        @GetMapping("/{familyId}/{pet_id}")
        public Set<Pet> getFamilyAnimals(@PathVariable Long familyId) {
            return familyService.getFamilyAnimals(familyId);
        }
}
