package br.com.lucas.contatos.repository;

import br.com.lucas.contatos.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

    public interface ContatoRepository extends JpaRepository<Contato, Long> {
        List<Contato> findByNomeContaining(String nome);
        List<Contato> findByDataNascimentoBetween(LocalDate startDate, LocalDate endDate);
    }

