package com.elberjsn.biblioteca.controle;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elberjsn.biblioteca.model.Livro;
import com.elberjsn.biblioteca.service.LivroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/livros")
public class ControllerLivro {

        @Autowired
        private LivroService livroService;

        @GetMapping("/")
        public List<Livro> getMethodName() {
            var todos = livroService.buscarTodosLivros();
            return todos;
        }

        @PostMapping("/")
        public ResponseEntity<Livro> postMethodName(@RequestParam String titulo, @RequestParam String autor,
        @RequestParam String isbn,@RequestParam Integer anoPublicacao,@RequestParam Integer qtdDisponivel ) {
            
           Livro livro = new Livro(null, titulo, autor, isbn, anoPublicacao, qtdDisponivel);
           var newLivro =livroService.salvarLivro(livro);
           return ResponseEntity.status(HttpStatus.CREATED).body(newLivro);
        }
        
        
}
