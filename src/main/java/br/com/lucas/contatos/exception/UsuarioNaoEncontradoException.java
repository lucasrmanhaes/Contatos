package br.com.lucas.contatos.exception;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
