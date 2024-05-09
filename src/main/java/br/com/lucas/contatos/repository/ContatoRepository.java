package br.com.lucas.contatos.repository;

import br.com.lucas.contatos.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContatoRepository extends JpaRepository<Contato, UUID> {

    List<Contato> findByDataNascimentoBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT c from Contato c WHERE c.nome = :nomeContato")
    Optional<Contato> buscarContatoPorNome(@Param("nomeContato") String nome);

}

