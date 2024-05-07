package br.com.lucas.contatos.dto;

import br.com.lucas.contatos.model.Contato;
import java.time.LocalDate;
import java.util.UUID;

//record: classe de dados que ja possui get/set, toString, equals e hashCode
public record ContatoExibicaoDto(UUID id, String nome, String email, String telefone, LocalDate dataNascimento) {

    //Construtor
    public ContatoExibicaoDto(Contato contato){
        this(
            contato.getId(),
            contato.getNome(),
            contato.getEmail(),
            contato.getTelefone(),
            contato.getDataNascimento()
        );
    }
}
