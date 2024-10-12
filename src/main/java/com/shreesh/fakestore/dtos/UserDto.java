package com.shreesh.fakestore.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private String name;
    private Boolean isEmailVerified;
    private String email;
    private List<role> roles;

}
