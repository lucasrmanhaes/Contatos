package br.com.lucas.contatos.advice;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler{

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> argumentosInvalidos(MethodArgumentNotValidException erro){
        //Chave:valor
        Map<String, String> mapaDeErro = new HashMap<String, String>();
        //Lista de fildErros do MethodArgumetnsNotValidException
        List<FieldError> campos = erro.getBindingResult().getFieldErrors();
        //Populando o chave:valor
        for(FieldError campo : campos){
            mapaDeErro.put(campo.getField(), campo.getDefaultMessage());
        }
        return mapaDeErro;
    }

}
