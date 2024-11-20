package com.elberjsn.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elberjsn.biblioteca.model.Livro;
import com.elberjsn.biblioteca.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro salvarLivro(Livro livro){
        return livroRepository.save(livro);
    }
    public List<Livro> buscarTodosLivros(){
        return livroRepository.findAll();
    }
    public Optional<Livro> buscarLivroId(Long id){
        return livroRepository.findById(id);
    }
    public List<Livro> buscarLivroNome(String nome){
        return livroRepository.findByNomeLivro(nome);
    }
    public List<Livro> buscarLivroIsbn(String isbn){
        return livroRepository.findByIsbn(isbn);
    }
    public List<Livro> buscarLivroAutor(String autor){
        return livroRepository.findByAutor(autor);
    }
    public void apagarLivro(Livro livro){
        livroRepository.delete(livro);
    }
    public Livro editarLivro(Livro livro){
        Livro onLivro = buscarLivroId(livro.getId()).get();
        
        onLivro.setTitulo(livro.getTitulo());
        onLivro.setQtdDisponivel(livro.getQtdDisponivel());
        onLivro.setIsbn(livro.getIsbn());
        onLivro.setAutor(livro.getAutor());
        onLivro.setAnoPublicacao(livro.getAnoPublicacao());

        return salvarLivro(onLivro);
    }



}
