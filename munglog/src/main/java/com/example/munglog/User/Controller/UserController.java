package com.example.munglog.User.Controller;

import com.example.munglog.User.DTO.*;
import com.example.munglog.User.Domain.FamilyRequest;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/register")
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

    @GetMapping("/user/{user_id}")
    public ResponseEntity<SignResponse> getUser(@RequestParam String userId) throws Exception {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

    @PutMapping("/user/update/{user_id}")
    public ResponseEntity<String> updateUserInformation(@RequestBody UserUpdateRequest request) {
        userService.updateUserInformation(request);
        return ResponseEntity.status(HttpStatus.OK).body("User information updated successfully.");
    }

    @DeleteMapping("/user/delete/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + userId);
        }
    }

    @PostMapping("/family/{userId}/families/{familyId}")
    public User addUserToFamily(@PathVariable Long userId, @PathVariable Long familyId) {
        return userService.addUserToFamily(userId, familyId);
    }

    @PostMapping("/{userId}/request-family/{familyId}")
    public FamilyRequest requestFamilyMembership(@PathVariable Long userId, @PathVariable Long familyId) {
        return userService.requestFamilyMembership(userId, familyId);
    }

    @GetMapping("/family/search/{user_id}")
    public List<User> searchUsers(@RequestParam String username) {
        return userService.searchUsers(username);
    }

    @PostMapping("/users/findid")
    public ResponseEntity<String> findUserId(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String phone = request.get("phone");

        Optional<String> userId = userService.findUserId(username, phone);

        return userId.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("User not found"));
    }

    @PostMapping("/users/findpw")
    public ResponseEntity<String> findUserPassword(@RequestBody Map<String, String> request) {
        String userid = request.get("userid");
        String username = request.get("username");
        String phone = request.get("phone");

        Optional<String> userPassword = userService.findUserPassword(userid, username, phone);

        return userPassword.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("User not found"));
    }
}
