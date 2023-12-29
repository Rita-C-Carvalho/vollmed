package br.com.vollmed.vollmed.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//@RestControllerAdvice É UMA ANOTAÇÃO PARA DIZER QUE A CLASSE É UMA CLASSE DO TIPO PARA TRATAR ERROS
@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){

        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    //CRIANDO UMA DTO PARA USAR NO TRATAMENTO DE ERRO 400

    private record DadosErroValidacao(String campo, String mensagem){

        public DadosErroValidacao (FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }

    }


}
