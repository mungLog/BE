package com.example.munglog.User.Service;

import com.example.munglog.User.DTO.SignResponse;
import com.example.munglog.User.DTO.UserDto;
import com.example.munglog.User.DTO.UserUpdateRequest;
import com.example.munglog.User.Domain.User;
import com.example.munglog.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public Long save(UserDto dto) {
        if (userRepository.findOneWithAuthoritiesByUsername(dto.getUserid()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        // 유저 정보를 만들어서 save
        User user = User.builder()
                .userid(dto.getUserid())
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .roles(false)  // 기본값으로 false 설정
                .build();

        return (long) userRepository.save(user).getId();
    }

    // 정보수정
    public void updateUserInformation(UserUpdateRequest request) {
        User user = userRepository.findByUserid(request.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with userid: " + request.getUserId()));

        user.setEmail(request.getNewEmail());
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setUsername(request.getNewUsername());
        user.setPhone(request.getNewPhone());
        userRepository.save(user);
    }

    public SignResponse getUser(String userid) throws Exception {
        User user = userRepository.findByUserid(userid)
                .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));
        return new SignResponse(user);
    }

    // 회원 탈퇴
    public void deleteUser(String userid) {
        User user = userRepository.findByUserid(userid)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다: " + userid));

        userRepository.delete(user);
    }

    // 유저, 권한 정보를 가져오는 메소드
    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }
}
