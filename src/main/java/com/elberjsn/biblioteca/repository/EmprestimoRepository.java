package com.elberjsn.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elberjsn.biblioteca.model.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo,Long> {
}
