package com.example.munglog.user.pet.controller;

import com.example.munglog.pet.dto.PetDTO;
import com.example.munglog.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<PetDTO> createPet(@RequestPart("pet") PetDTO petDTO, @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(petService.createPet(petDTO, file));
    }

    @GetMapping("/{pet_id}")
    public ResponseEntity<PetDTO> getPet(@PathVariable Long pet_id) {
        return ResponseEntity.ok(petService.getPet(pet_id));
    }

    @PostMapping("/update/{pet_id}")
    public ResponseEntity<PetDTO> updatePet(@PathVariable Long pet_id, @RequestPart("pet") PetDTO petDTO, @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(petService.updatePet(pet_id, petDTO, file));
    }

    @DeleteMapping("/delete/{pet_id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long pet_id) {
        petService.deletePet(pet_id);
        return ResponseEntity.ok().build();
    }
}
