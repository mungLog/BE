package com.example.munglog.User.Service;

import com.example.munglog.User.DTO.CustomUserInfoDto;
import com.example.munglog.User.DTO.LoginRequestDto;
import com.example.munglog.User.Domain.User;
import com.example.munglog.User.JWT.JWTUtil;
import com.example.munglog.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
        private final JWTUtil jwtUtil;
        private final UserRepository userRepository;
        private final PasswordEncoder encoder;
        private final ModelMapper modelMapper;

        @Transactional
        public String login(LoginRequestDto dto) {
            String userid = dto.getUserid();
            String password = dto.getPassword();
            User member = userRepository.findUserByuserId(userid);
            if(member == null) {
                throw new UsernameNotFoundException("아이디가 존재하지 않습니다.");
            }

            // 암호화된 password를 디코딩한 값과 입력한 패스워드 값이 다르면 null 반환
            if(!encoder.matches(password, member.getPassword())) {
                throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
            }

            CustomUserInfoDto info = modelMapper.map(member, CustomUserInfoDto.class);

            String accessToken = jwtUtil.createAccessToken(info);
            return accessToken;
        }
    }
