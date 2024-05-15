package br.com.lucas.contatos.dto;

import java.util.UUID;

public record UsuarioExibicaoDto(
            UUID idUsuario,
            String nome,
            String email
)
{ }
