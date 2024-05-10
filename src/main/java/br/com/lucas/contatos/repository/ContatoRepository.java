package br.com.lucas.contatos.repository;

import br.com.lucas.contatos.model.Contato;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContatoRepository extends JpaRepository<Contato, UUID> {

    @Query("SELECT c FROM Contato c WHERE c.dataNascimento BETWEEN :dataInicial AND :dataFinal")
    List<Contato> findByDataNascimentoBetween(@Param("dataInicial") LocalDate dataInicial,@Param("dataFinal") LocalDate dataFinal);

    @Query("SELECT c from Contato c WHERE c.nome = :nomeContato")
    Optional<Contato> buscarContatoPorNome(@Param("nomeContato") String nome);

    Optional<Contato> findByNome(String nome);

}

