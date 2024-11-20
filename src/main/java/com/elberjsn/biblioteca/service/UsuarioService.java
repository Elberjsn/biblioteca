package com.elberjsn.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elberjsn.biblioteca.model.Usuario;
import com.elberjsn.biblioteca.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario user){
        return usuarioRepository.save(user);
    }
    public Usuario buscarID(Long id){
        return usuarioRepository.findById(id).get();
    }
    public Usuario editarUsuario(Usuario usuario){
        Usuario userOld = buscarID(usuario.getId());

        userOld.setNome(usuario.getNome());
        userOld.setTelefone(usuario.getTelefone());
        userOld.setEmail(usuario.getEmail());
        userOld.setCpf(usuario.getCpf());

        return usuarioRepository.save(userOld);
    }
    public void deletarUsuario(Usuario user){
        usuarioRepository.delete(user);
    }

}
