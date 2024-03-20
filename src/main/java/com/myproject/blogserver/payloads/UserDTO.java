package com.myproject.blogserver.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;

    @NotBlank
    private String name;

    @NotBlank
    @Email()
    private String email;

    @NotBlank
    private String about;

    @NotBlank
    @Size(min = 4, max = 8, message = "Message must be of minimum 4 characters")
    private String password;

}
