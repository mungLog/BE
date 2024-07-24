package com.example.munglog.User.Controller;

import com.example.munglog.User.DTO.*;
import com.example.munglog.User.Domain.User;
import com.example.munglog.User.Repository.UserRepository;
import com.example.munglog.User.Service.AuthService;
import com.example.munglog.User.Service.UserService;
import com.example.munglog.User.config.CommonResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse> join(@RequestBody UserDto userDTO){
        userService.save(userDTO);
        CommonResponse res = new CommonResponse(
                200,
                HttpStatus.OK,
                " 성공",
                null
        );
        return new ResponseEntity<>(res, res.getHttpStatus());
    }

    @PostMapping("/idCheck")
    public ResponseEntity<CommonResponse> checkUserId(@RequestBody UserIdRequest request) {
        String userId = request.getUserId();
        Optional<User> userOptional = userRepository.findFirstByUserid(userId);
        CommonResponse res;
        if (userOptional.isPresent()) {
            res = new CommonResponse(400, HttpStatus.BAD_REQUEST, "아이디가 이미 존재합니다.", null);
            return new ResponseEntity<>(res, res.getHttpStatus());
        } else {
            res = new CommonResponse(200, HttpStatus.OK, "사용 가능한 아이디입니다.", null);
            return new ResponseEntity<>(res, res.getHttpStatus());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<SignResponse> getUser(@RequestParam String userId) throws Exception {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUserInformation(@RequestBody UserUpdateRequest request) {
        userService.updateUserInformation(request);
        return ResponseEntity.status(HttpStatus.OK).body("User information updated successfully.");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + userId);
        }
    }
}
