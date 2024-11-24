package com.elberjsn.biblioteca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elberjsn.biblioteca.model.Administrador;
import com.elberjsn.biblioteca.repository.AdministradorRepository;

import jakarta.transaction.Transactional;

@Service
public class AdministradorService {

    @Autowired
    AdministradorRepository administradorRepository;

    public Administrador salvar(Administrador adm) {
        return administradorRepository.save(adm);
    }

    @Transactional()
    public Administrador buscarPorId(Long id) {
        var admbyId = administradorRepository.findById(id);
        return admbyId.get();

    }

    public Administrador editarAdministrador(Administrador newadm) {
        Administrador adm = buscarPorId(newadm.getId());

        if (adm.getId()!= null){
            return null;
        }

        adm.setNome(newadm.getNome());
        adm.setEmail(newadm.getEmail());
        adm.setSenha(newadm.getSenha());

        return administradorRepository.save(adm);
    }

    public void deletarUsuario(Administrador user) {
        administradorRepository.delete(user);
    }

    public Administrador logarAdministrador(Administrador adm) {
        Optional<Administrador> admLogado = administradorRepository.findByEmailAndSenha(adm.getEmail(), adm.getSenha());
        if (admLogado.isPresent()){
            return admLogado.get();
        }else{
            return adm;
        }


    }

}
