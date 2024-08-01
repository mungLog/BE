package com.example.munglog.User.Controller;

import com.example.munglog.User.DTO.LoginRequestDto;
import com.example.munglog.User.DTO.UserDto;
import com.example.munglog.User.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserDto> getMemberProfile(
            @Valid @RequestBody LoginRequestDto request
    ) {
        UserDto userDto = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }
}

