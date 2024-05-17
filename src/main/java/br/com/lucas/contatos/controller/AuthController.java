package br.com.lucas.contatos.controller;

import br.com.lucas.contatos.dto.UsuarioLoginDto;
import br.com.lucas.contatos.dto.UsuarioCadastroDto;
import br.com.lucas.contatos.dto.UsuarioExibicaoDto;
import br.com.lucas.contatos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    //Injeção de dependencia do gerenciador de autenticação
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioLoginDto usuarioLoginDto){

        //Classe responsavel por representar o usuario e senha
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.email(),
                usuarioLoginDto.senha()
        );

        //Classe que representa a autenticação que necessita de um gerenciador de autenticação
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        //Se a autenticação ocorrer será retornado um ok
        return ResponseEntity.ok().body(auth);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto registrar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto){
        UsuarioExibicaoDto usuarioSalvo = null;
        return usuarioSalvo = usuarioService.gravar(usuarioCadastroDto);
    }
}
