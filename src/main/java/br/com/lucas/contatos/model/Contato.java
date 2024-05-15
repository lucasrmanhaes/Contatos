package br.com.lucas.contatos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity()
@Table(name = "TB_CONTATOS")
public class Contato {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String nome;
    private String telefone;
    private String email;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

}
