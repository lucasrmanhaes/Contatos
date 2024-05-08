package br.com.lucas.contatos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDate;
import java.util.UUID;

public record ContatoCadastroDto(
        UUID id,
        @NotBlank(message = "O campo nome é obrigatório")
        String nome,
        @NotBlank(message = "O campo e-mail é obrigatório" )
        @Email(message = "O campo e-mail está incorreto")
        String email,
        @NotBlank(message = "O campo telefone é obrigátorio")
        String telefone,
        @NotNull(message = "O campo data de nascimento é obrigatório")
        LocalDate dataNascimento
)
{}
