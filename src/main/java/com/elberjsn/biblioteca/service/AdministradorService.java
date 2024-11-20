package com.elberjsn.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elberjsn.biblioteca.model.Administrador;
import com.elberjsn.biblioteca.repository.AdministradorRepository;

@Service
public class AdministradorService {
    
    @Autowired
    AdministradorRepository administradorRepository;

    public Administrador salvar(Administrador adm){
        return administradorRepository.save(adm);
    }
    public Administrador buscarID(Long id){
        return administradorRepository.findById(id).get();
    }
    public Administrador editarAdministrador(Administrador newadm){
        Administrador adm = buscarID(newadm.getId());

        adm.setNome(newadm.getNome());
        adm.setEmail(newadm.getEmail());
        adm.setSenha(newadm.getSenha());

        return administradorRepository.save(adm);
    }
    public void deletarUsuario(Administrador user){
        administradorRepository.delete(user);
    }

}
