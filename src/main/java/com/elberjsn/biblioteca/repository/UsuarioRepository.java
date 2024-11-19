package com.elberjsn.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elberjsn.biblioteca.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
