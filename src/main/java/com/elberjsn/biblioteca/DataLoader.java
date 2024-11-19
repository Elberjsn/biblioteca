package com.elberjsn.biblioteca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.elberjsn.biblioteca.model.Livro;
import com.elberjsn.biblioteca.model.Usuario;
import com.elberjsn.biblioteca.repository.LivroRepository;
import com.elberjsn.biblioteca.repository.UsuarioRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        Livro livro1 = new Livro(null, "1984", "George Orwell", "9780451524935", 1949, 3);
        Livro livro2 = new Livro(null, "A Revolução dos Bichos", "George Orwell", "9780451526342", 1945, 4);
        livroRepository.saveAll(List.of(livro1, livro2));

        Usuario usuario = new Usuario(null, "Maria Oliveira", "maria@email.com", "1199999999", "98765432100");
        usuarioRepository.save(usuario);
    }
}
