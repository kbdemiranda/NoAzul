package io.github.noazul.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;

}
