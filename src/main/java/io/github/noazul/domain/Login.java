package io.github.noazul.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Login {

    @NotNull @NotEmpty
    private String email;
    @NotNull
    private String senha;

    public UsernamePasswordAuthenticationToken getUser() {
        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }
}
