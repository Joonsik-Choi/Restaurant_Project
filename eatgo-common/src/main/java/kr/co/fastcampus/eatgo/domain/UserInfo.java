package kr.co.fastcampus.eatgo.domain;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String id;
    private String email;
    private String password;
    private String name;
    private String gender;
}
