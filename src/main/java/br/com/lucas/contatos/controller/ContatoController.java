package br.com.lucas.contatos.controller;

import br.com.lucas.contatos.dto.ContatoCadastroDto;
import br.com.lucas.contatos.dto.ContatoExibicaoDto;
import br.com.lucas.contatos.model.Contato;
import br.com.lucas.contatos.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @PostMapping("/contatos")
    @ResponseStatus(HttpStatus.CREATED)
    public ContatoExibicaoDto gravar(@RequestBody @Valid ContatoCadastroDto contatoCadastroDto){
        return service.gravar(contatoCadastroDto);
    }

    @GetMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDto buscarPorId(@PathVariable("id") UUID id){
        return service.buscarPeloId(id);
    }

    @GetMapping("/contatos/nome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDto buscarPorNomePath(@PathVariable("nome") String nome){
        return service.buscarPorNome(nome);
    }

    @GetMapping(value = "/contatos", params = "nome")
    public ContatoExibicaoDto buscarPorNomeParametro(@RequestParam("nome") String nome){
        return service.buscarPorNome(nome);
    }

    @GetMapping("/contatos/listar")
    @ResponseStatus(HttpStatus.OK)
    public Page<ContatoExibicaoDto> buscarTodos(Pageable paginacao){
        return service.buscarTodos(paginacao);
    }

    @GetMapping(value = "/contatos", params = {"dataInicio", "dataFinal"})
    @ResponseStatus(HttpStatus.OK)
    public List<ContatoExibicaoDto> buscarPelaData(@RequestParam("dataInicio") LocalDate dataInicial,@RequestParam("dataFinal") LocalDate dataFinal){
        return service.buscarAniversariantes(dataInicial, dataFinal);
    }

    @PutMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDto atualizar(@RequestBody Contato contato){
        return service.atualizar(contato);
    }

    @DeleteMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") UUID id){
        service.remover(id);
    }

}

