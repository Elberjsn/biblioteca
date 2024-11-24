package com.elberjsn.biblioteca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.elberjsn.biblioteca.model.Administrador;
import com.elberjsn.biblioteca.model.Emprestimo;
import com.elberjsn.biblioteca.model.Livro;
import com.elberjsn.biblioteca.model.Usuario;
import com.elberjsn.biblioteca.repository.AdministradorRepository;
import com.elberjsn.biblioteca.repository.LivroRepository;
import com.elberjsn.biblioteca.repository.UsuarioRepository;
import com.elberjsn.biblioteca.service.EmprestimoService;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private EmprestimoService emprestimoService;

    @Override
    public void run(String... args) throws Exception {
        Livro livro1 = new Livro(null, "1984", "George Orwell", "9780451524935", 1949, 3, null);
        Livro livro2 = new Livro(null, "A Revolução dos Bichos", "George Orwell", "9780451526342", 1945, 4, null);
        livroRepository.saveAll(List.of(livro1, livro2));

        Usuario usuario1 = new Usuario(null, "Maria Oliveira", "maria@email.com", "1199999999", "98765432100");
        Usuario usuario2 = new Usuario(null, "Maria Oliveira", "Oliveira@email.com", "1199899999", "88765432100");
        usuarioRepository.saveAll(List.of(usuario1,usuario2));
        
        Administrador adm = new Administrador(null, "testeadm", "testeadm@adm", "adm");
        administradorRepository.save(adm);

        Emprestimo emp=new Emprestimo(null, null, null, null, usuario2, livro2);
        Emprestimo emp2=new Emprestimo(null, null, null, null, usuario1, livro2);

        emprestimoService.novoEmprestimo(emp);
        emprestimoService.novoEmprestimo(emp2);
    }
}
