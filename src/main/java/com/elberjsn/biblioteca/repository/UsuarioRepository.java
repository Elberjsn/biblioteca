package com.elberjsn.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elberjsn.biblioteca.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    List<Usuario> findByNomeContainingIgnoreCase(String nome);
    List<Usuario> findByEmailContainingIgnoreCase(String email);
    List<Usuario> findByCpfContaining(String cpf);
}
