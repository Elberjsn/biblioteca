package com.elberjsn.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.elberjsn.biblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro,Long> {

    @Query("FROM Livro L WHERE L.titulo LIKE %:nome%")
    List<Livro> findByNomeLivro(String nome);

    @Query("FROM Livro L WHERE L.isbn LIKE %:isbn%")
    List<Livro> findByIsbn(String isbn);

    @Query("FROM Livro L WHERE L.autor LIKE %:autor%")
    List<Livro> findByAutor(String autor);
}
