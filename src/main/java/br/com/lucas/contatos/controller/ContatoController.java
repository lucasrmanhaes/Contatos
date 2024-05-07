package br.com.lucas.contatos.controller;

import br.com.lucas.contatos.model.Contato;
import br.com.lucas.contatos.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @PostMapping("/contatos")
    @ResponseStatus(HttpStatus.CREATED)
    public Contato gravar(@RequestBody Contato contato){
        return contatoService.gravar(contato);
    }

    @GetMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Contato> buscarPorId(@PathVariable("id") UUID id){
        return contatoService.buscarPorId(id);
    }

    @GetMapping("/contatos/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<Contato> buscarTodos(){
        return contatoService.buscarTodos();
    }

    @PutMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public Contato atualizar(@RequestBody Contato contato){
        return contatoService.atualizar(contato);
    }

    @DeleteMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") UUID id){
        contatoService.remover(id);
    }

    @GetMapping("/contatos/{startDate}/{endDate}")
    @ResponseStatus(HttpStatus.OK)
    public List<Contato> buscarPelaData(@PathVariable("startDate") LocalDate dataInicial,@PathVariable("endDate") LocalDate dataFinal){
        return contatoService.buscarAniversariantes(dataInicial, dataFinal);
    }
}

