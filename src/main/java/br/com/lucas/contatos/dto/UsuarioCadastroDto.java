package br.com.lucas.contatos.dto;

import br.com.lucas.contatos.model.UsuarioRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record UsuarioCadastroDto(
        UUID idUsuario,
        @NotBlank(message = "O nome do usuário é obrigatório")
        String nome,
        @NotBlank(message = "O e-mail do usuário é obrigatório")
        @Email(message = "O e-mail do usuário não é válido")
        String email,
        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 5 e 20 caracteres")
        String senha,
        @Enumerated(EnumType.STRING)
        UsuarioRole role
) {
}
