package br.com.lucas.contatos.controller;

import br.com.lucas.contatos.dto.ContatoExibicaoDto;
import br.com.lucas.contatos.model.Contato;
import br.com.lucas.contatos.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ContatoExibicaoDto gravar(@RequestBody Contato contato){
        return service.gravar(contato);
    }

    @GetMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDto buscarPorId(@PathVariable("id") UUID id){
        return service.buscarPeloId(id);
    }

    @GetMapping("/contatos/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<ContatoExibicaoDto> buscarTodos(){
        return service.buscarTodos();
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

    @GetMapping("/contatos/{startDate}/{endDate}")
    @ResponseStatus(HttpStatus.OK)
    public List<ContatoExibicaoDto> buscarPelaData(@PathVariable("startDate") LocalDate dataInicial,@PathVariable("endDate") LocalDate dataFinal){
        return service.buscarAniversariantes(dataInicial, dataFinal);
    }
}

