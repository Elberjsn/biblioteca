package com.elberjsn.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.elberjsn.biblioteca.model.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo,Long> {

    @Query("FROM Emprestimo WHERE usuario = :id")
    Optional<Emprestimo> nuscarPorUsuario_emprestimo(Long id);
}
