package br.com.lucas.contatos.controller;

import br.com.lucas.contatos.dto.UsuarioCadastroDto;
import br.com.lucas.contatos.dto.UsuarioExibicaoDto;
import br.com.lucas.contatos.model.Usuario;
import br.com.lucas.contatos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuario")
    public UsuarioExibicaoDto gravar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto){
        return usuarioService.gravar(usuarioCadastroDto);
    }

    @GetMapping("/usuario/{id}")
    public UsuarioExibicaoDto listar(@PathVariable("id") UUID id){
        return usuarioService.listar(id);
    }

    @PutMapping("/usuario")
    public UsuarioExibicaoDto atualizar(@RequestBody Usuario usuario){
        return usuarioService.atualizar(usuario);
    }

    @DeleteMapping("/usuario/{id}")
    public void remover(@PathVariable("id") UUID id){
        usuarioService.remover(id);
    }

}
