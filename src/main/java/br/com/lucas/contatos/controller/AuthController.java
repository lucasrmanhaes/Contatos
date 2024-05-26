package br.com.lucas.contatos.controller;

import br.com.lucas.contatos.service.TokenService;
import br.com.lucas.contatos.dto.TokenDto;
import br.com.lucas.contatos.dto.UsuarioLoginDto;
import br.com.lucas.contatos.dto.UsuarioCadastroDto;
import br.com.lucas.contatos.dto.UsuarioExibicaoDto;
import br.com.lucas.contatos.model.Usuario;
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
    //Injeção de dependencia do UsuarioService
    @Autowired
    private UsuarioService usuarioService;
    //Injeção de dependencia do TokenService
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioLoginDto usuarioLoginDto){

        //Classe responsavel por representar o usuario e senha
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.email(),
                usuarioLoginDto.senha()
        );

        //Classe que representa a autenticação que necessita de um gerenciador de autenticação
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        //Para gerar um token iremos usar o auth que com o metodo getPrincipal() retorna um object então iremos
        //fazer um casting para transformar esse objeto generico em um objeto especifico do tipo Usuario
        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        //Se a autenticação ocorrer será retornado o token para o usuario
        return ResponseEntity.ok(new TokenDto(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto registrar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto){
        UsuarioExibicaoDto usuarioSalvo = null;
        return usuarioSalvo = usuarioService.gravar(usuarioCadastroDto);
    }
}
