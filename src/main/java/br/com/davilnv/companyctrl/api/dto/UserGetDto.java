package br.com.davilnv.companyctrl.api.dto;

import br.com.davilnv.companyctrl.api.models.user.UserModel;
import lombok.Data;

@Data
public class UserGetDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public UserGetDto(UserModel userModel) {
        this.id = userModel.getId();
        this.username = userModel.getUsername();
        this.firstName = userModel.getFirstName();
        this.lastName = userModel.getLastName();
        this.email = userModel.getEmail();
    }
}
