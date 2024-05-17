package br.com.lucas.contatos.dto;

import br.com.lucas.contatos.model.Usuario;
import br.com.lucas.contatos.model.UsuarioRole;

import java.util.UUID;

public record UsuarioExibicaoDto(UUID idUsuario, String nome, String email, UsuarioRole role)
{
    public UsuarioExibicaoDto(Usuario usuario){
        this(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}
