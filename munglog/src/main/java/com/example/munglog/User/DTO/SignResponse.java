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
    private String Email;
    private String Phone;
    private String token;

    public SignResponse(User user){
        this.id = (long) user.getId();
        this.userId = user.getUserid();
        this.username = user.getUsername();
        this.Email = user.getEmail();
        this.Phone = user.getPhone();

    }

}
