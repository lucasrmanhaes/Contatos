package br.com.lucas.contatos.exception;

public class UsuarioExisteException extends RuntimeException{
    public UsuarioExisteException(String mensagem){
        super(mensagem);
    }
}
