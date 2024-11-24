package com.elberjsn.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elberjsn.biblioteca.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    Optional<Administrador> findByEmailAndSenha(String email, String senha);
}
