package br.com.lucas.contatos.dto;

import java.time.LocalDate;
import java.util.UUID;

public record ContatoCadastroDto(
        UUID id,
        String nome,
        //String email,
        String telefone,
        LocalDate dataNascimento
) {}
