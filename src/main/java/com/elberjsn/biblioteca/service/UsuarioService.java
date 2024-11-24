package com.elberjsn.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elberjsn.biblioteca.model.Usuario;
import com.elberjsn.biblioteca.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario user) {
        return usuarioRepository.save(user);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).get();
    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Integer contarTodosUsuarios() {
        List<Usuario> listas = buscarTodosUsuarios();
        return listas.size();
    }

    public List<Usuario> buscarPorNome(String nome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Usuario> buscarPorCPF(String cpf) {
        return usuarioRepository.findByCpfContaining(cpf);
    }

    public List<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmailContainingIgnoreCase(email);
    }

    public Usuario editarUsuario(Usuario usuario)  {
        Usuario userOld = buscarPorId(usuario.getId());

        if (userOld.getId()!=null) {

            userOld.setNome(usuario.getNome());
            userOld.setTelefone(usuario.getTelefone());
            userOld.setEmail(usuario.getEmail());
            userOld.setCpf(usuario.getCpf());
            return usuarioRepository.save(userOld);
        }
        return usuario;
    }

    public void deletarUsuario(Usuario user) {
        usuarioRepository.delete(user);
    }

}
