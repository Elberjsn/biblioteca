package com.elberjsn.biblioteca.exception;

import java.util.NoSuchElementException;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> noResourceFoundException(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi Possivel Encontrar o caminho Solicitado: /"+ex.getResourcePath());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> errorNotFoundException(NotFoundException msg){
        return ResponseEntity.status(HttpStatus.NOT_FOUND ).body("Não foi Possivel Encontrar os dados:"+msg.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> emptyErro(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi Possivel Retornar dados:"+ex.getMessage());
    }
    

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<String> errorNotFound(NotFound ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND ).body(ex.getMessage());
    }
    //@ExceptionHandler(Exception.class)
    //public ResponseEntity<String> erroGlobal(Exception ex){
    //    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    //}
    

}
