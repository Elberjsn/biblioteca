package com.elberjsn.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elberjsn.biblioteca.model.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo,Long> {
    List<Emprestimo> findByUsuarioId(Long usuario);
    List<Emprestimo> findByUsuarioNomeContaining(String usuario);
    List<Emprestimo> findByLivroId(Long livro);
    List<Emprestimo> findByLivroTituloContaining(String livro);
}
