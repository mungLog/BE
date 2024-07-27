package com.example.munglog.User.Controller;

import com.example.munglog.User.Domain.FamilyRequest;
import com.example.munglog.User.Service.FamilyService;
import com.example.munglog.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/families")
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

//        @PostMapping("/{familyId}/animals")
//        public Animal addAnimalToFamily(@PathVariable Long familyId, @RequestBody Animal animal) {
//            return familyService.addAnimalToFamily(familyId, animal);
//        }
//
//        @GetMapping("/{familyId}/animals")
//        public Set<Animal> getFamilyAnimals(@PathVariable Long familyId) {
//            return familyService.getFamilyAnimals(familyId);
//        }
}
