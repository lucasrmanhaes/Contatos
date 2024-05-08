package br.com.lucas.contatos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContatoNaoEncontradoException extends RuntimeException{

    //Construtor - quem chamar ContatoNãoEncontradoException tem que passar uma String
    //Essa String será enviada para o construtor de RUntimeException
    public ContatoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

}
