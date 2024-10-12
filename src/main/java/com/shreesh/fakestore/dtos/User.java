package com.shreesh.fakestore.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class User{

    public User(){}

    private String name;
    private Boolean isEmailVerified;
    private String email;
    private String password;


    private List<role> roles;



}
