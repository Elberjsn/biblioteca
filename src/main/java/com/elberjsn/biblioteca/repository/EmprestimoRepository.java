package com.elberjsn.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elberjsn.biblioteca.model.Emprestimo;
import com.elberjsn.biblioteca.model.Usuario;

public interface EmprestimoRepository extends JpaRepository<Emprestimo,Long> {
    List<Emprestimo> findByUsuario_emprestimo(Usuario usuario_emprestimo);
}
