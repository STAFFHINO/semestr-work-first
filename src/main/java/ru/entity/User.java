package ru.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    private String nickname;
    private String login;
    private String password;
    private String role;
}
