package com.elberjsn.biblioteca.controle;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elberjsn.biblioteca.model.Livro;
import com.elberjsn.biblioteca.service.LivroService;
import com.google.gson.Gson;





@RestController
@RequestMapping("/livros")
public class LivroController {

        @Autowired
        private LivroService livroService;

        @GetMapping("/")
        public List<Livro> buscarTodos() {
            var todos = livroService.buscarTodosLivros();
            return todos;
        }

        @PostMapping("/salvar")
        public ResponseEntity<Livro> salvarLivro(@RequestBody String newLivroJson ) {
            
            Livro livro = retornaLivro(newLivroJson);
            var newLivro = livroService.salvarLivro(livro);
           
            if (newLivro.getId() != null){
                return ResponseEntity.status(HttpStatus.CREATED).body(newLivro);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
            }
        }

        @GetMapping("/buscar/{metodo}")
        public ResponseEntity<List<Livro>> buscaLivro(@RequestParam String texto, @PathVariable String metodo) {

            List<Livro> lista = new ArrayList<>();

            switch (metodo) {
                case "titulo":
                    lista = livroService.buscarLivroNome(texto);
                break;
                case "isbn":
                    lista = livroService.buscarLivroIsbn(texto);
                break;

                case "autor":
                    lista = livroService.buscarLivroAutor(texto);
                break;
            
                default:
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.status(HttpStatus.OK).body(lista);  
        }

        @PutMapping("/editar/{id}")
        public ResponseEntity<Livro> editarLivro(@PathVariable String id, @RequestBody String jsonLivro) {
            
            Livro livro = retornaLivro(jsonLivro);
            Livro newLivro = livroService.editarLivro(livro);

            return ResponseEntity.ok(newLivro);
        }

        @DeleteMapping("/deletar/{id}")
        public ResponseEntity<String> editarLivro(@PathVariable String id) {
            
            Optional<Livro> livro = livroService.buscarLivroId(Long.parseLong(id));

            if (livro.isPresent()) {
                livroService.apagarLivro(livro.get());
            }else{
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body("Livro: "+id+", Apagado!");
        }
 
        
        public Livro retornaLivro(String json){
            Gson jGson = new Gson();
            return jGson.fromJson(json, Livro.class);
        }
        
        
}
