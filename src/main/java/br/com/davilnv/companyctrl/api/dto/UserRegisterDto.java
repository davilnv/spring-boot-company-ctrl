package br.com.davilnv.companyctrl.api.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
