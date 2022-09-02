package br.com.quatty.backend.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "It is necessary to enter a username to create a user")
    private String username;
    @NotBlank(message = "It is necessary to enter a password to create a user")
    private String password;
    @Email(message = "You must enter a valid email to create a new account.")
    private String email;
}
