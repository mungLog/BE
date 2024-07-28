package com.example.munglog.pet.service;

import com.example.munglog.pet.domain.Pet;
import com.example.munglog.pet.dto.PetDTO;
import com.example.munglog.pet.repository.PetRepository;
import com.example.munglog.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ImageUtil imageUtil;

    public PetDTO createPet(PetDTO petDTO, MultipartFile file) throws IOException {
        Pet pet = new Pet();
        pet.setUserId(petDTO.getUserId());
        pet.setName(petDTO.getName());
        pet.setBreed(petDTO.getBreed());
        pet.setAge(petDTO.getAge());
        pet.setWeight(petDTO.getWeight());
        pet.setDate(LocalDate.parse(petDTO.getDate()));
        pet.setTimestamp(petDTO.getTimestamp());
        pet.setNeutered(petDTO.isNeutered());
        pet.setGender(petDTO.getGender());  // 성별 추가

        String imageUrl = imageUtil.saveImage(file);
        pet.setImageUrl(imageUrl);

        // 일일대사량 계산
        double dailyKcal = calculateDailyKcal(pet.getWeight(), pet.isNeutered());
        petDTO.setDailyKcal(dailyKcal);

        pet = petRepository.save(pet);
        petDTO.setId(pet.getId());
        petDTO.setImageUrl(imageUrl);
        return petDTO;
    }

    public PetDTO getPet(Long petId) {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            PetDTO petDTO = new PetDTO();
            petDTO.setId(pet.getId());
            petDTO.setUserId(pet.getUserId());
            petDTO.setName(pet.getName());
            petDTO.setBreed(pet.getBreed());
            petDTO.setAge(pet.getAge());
            petDTO.setWeight(pet.getWeight());
            petDTO.setDate(pet.getDate().toString());
            petDTO.setTimestamp(pet.getTimestamp());
            petDTO.setImageUrl(pet.getImageUrl());
            petDTO.setNeutered(pet.isNeutered());
            petDTO.setGender(pet.getGender());  // 성별 추가

            // 일일대사량 계산
            double dailyKcal = calculateDailyKcal(pet.getWeight(), pet.isNeutered());
            petDTO.setDailyKcal(dailyKcal);

            return petDTO;
        }
        return null;
    }

    public PetDTO updatePet(Long petId, PetDTO petDTO, MultipartFile file) throws IOException {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            pet.setUserId(petDTO.getUserId());
            pet.setName(petDTO.getName());
            pet.setBreed(petDTO.getBreed());
            pet.setAge(petDTO.getAge());
            pet.setWeight(petDTO.getWeight());
            pet.setDate(LocalDate.parse(petDTO.getDate()));
            pet.setTimestamp(petDTO.getTimestamp());
            pet.setNeutered(petDTO.isNeutered());
            pet.setGender(petDTO.getGender());  // 성별 추가

            String imageUrl = imageUtil.saveImage(file);
            pet.setImageUrl(imageUrl);

            // 일일대사량 계산
            double dailyKcal = calculateDailyKcal(pet.getWeight(), pet.isNeutered());
            petDTO.setDailyKcal(dailyKcal);

            pet = petRepository.save(pet);
            petDTO.setId(pet.getId());
            petDTO.setImageUrl(imageUrl);
            return petDTO;
        }
        return null;
    }

    public void deletePet(Long petId) {
        petRepository.deleteById(petId);
    }

    private double calculateDailyKcal(double weight, boolean isNeutered) {
        if (isNeutered) {
            return (30 * weight + 70) * 1.6;
        } else {
            return (30 * weight + 70) * 1.8;
        }
    }
}
