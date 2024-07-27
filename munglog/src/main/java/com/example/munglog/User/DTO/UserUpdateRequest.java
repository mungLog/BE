package com.example.munglog.User.DTO;


import lombok.Data;

@Data
public class UserUpdateRequest {
    private String userId;
    private String newEmail;
    private String newPassword;
    private String newUsername;
    private String newPhone;
    private String newNickname;

}
