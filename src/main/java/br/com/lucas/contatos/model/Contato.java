package br.com.lucas.contatos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_CONTATOS")
@Data
public class Contato {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String nome;
    private String telefone;
    private String email;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Objects.equals(id, contato.id) && Objects.equals(nome, contato.nome) && Objects.equals(telefone, contato.telefone) && Objects.equals(email, contato.email) && Objects.equals(dataNascimento, contato.dataNascimento);
    }

}
