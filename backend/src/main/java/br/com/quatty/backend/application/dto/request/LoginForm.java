package br.com.quatty.backend.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

    @NotNull
    private String emailOrUsername;
    @NotNull
    private String password;

    public UsernamePasswordAuthenticationToken usernameAndPasswordToToken(){
        return new UsernamePasswordAuthenticationToken(emailOrUsername, password);
    }
}
