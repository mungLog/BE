package com.example.munglog.User.DTO;

import com.example.munglog.User.Domain.User;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignResponse {

    private Long id;
    private String userId;
    private String username;
    private String email;
    private String phone;
    private String nickname;
    private String token;
    private boolean roles;

    public SignResponse(User user) {
        this.id = (long) user.getId();
        this.userId = user.getUserid();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.nickname = user.getNickname();
        this.roles = user.isRoles();
    }
}
