package imoon.dtos;

import lombok.Data;

@Data
public class RegistrationUserDto {
    private String userName;
    private String password;
    private String confirmPassword;
    private String email;

}
