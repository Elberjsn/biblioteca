package com.elberjsn.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elberjsn.biblioteca.model.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo,Long> {


    List<Emprestimo> findByUsuarioContaining(Long idUser);

    List<Emprestimo> findByLivroContaining(Long idLivro); 
}
