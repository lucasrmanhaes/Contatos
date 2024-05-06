package br.com.lucas.contatos.controller;

import br.com.lucas.contatos.model.Contato;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/contatos")
    public class ContatoController {

        @PostMapping("/")
        public Contato create(@RequestBody Contato contato){
            return contato;
        }

    }

