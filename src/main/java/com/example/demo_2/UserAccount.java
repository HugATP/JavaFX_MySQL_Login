package com.example.demo_2;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
    private Integer userID;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
