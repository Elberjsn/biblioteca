package com.elberjsn.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elberjsn.biblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro,Long> {
 
}
