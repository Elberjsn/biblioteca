package com.elberjsn.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.elberjsn.biblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro,Long> {

    @Query("FROM Livro L WHERE L.titulo LIKE %:nome%")
    Optional<Livro> findByNomeLivro(String nome);

    @Query("FROM Livro L WHERE L.isbn LIKE %:isbn%")
    Optional<Livro> findByIsbn(String isbn);

    @Query("FROM Livro L WHERE L.autor LIKE %:autor%")
    Optional<Livro> findByAutor(String autor);
}
