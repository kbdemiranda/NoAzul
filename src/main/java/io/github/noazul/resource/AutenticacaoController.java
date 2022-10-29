package io.github.noazul.resource;

import io.github.noazul.domain.Login;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    //TODO: Finalizar a logica de autenticação com token jwt
    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid Login login){
        return null;
    }

}
