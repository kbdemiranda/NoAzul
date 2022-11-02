package io.github.noazul.resource;

import io.github.noazul.domain.Login;
import io.github.noazul.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    //TODO: Finalizar a logica de autenticação com token jwt
    @PostMapping
    public ResponseEntity<String> autenticar(@RequestBody @Valid Login login){
        try{
            Authentication authentication = authenticationManager.authenticate(login.getUser());
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok().body("Bearer " + token);
        }catch (AuthenticationException exception){
            return ResponseEntity.badRequest().build();
        }

    }

}
