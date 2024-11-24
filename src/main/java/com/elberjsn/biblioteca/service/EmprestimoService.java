package com.elberjsn.biblioteca.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elberjsn.biblioteca.model.Emprestimo;
import com.elberjsn.biblioteca.model.StatusEmpretimo;
import com.elberjsn.biblioteca.model.Usuario;
import com.elberjsn.biblioteca.repository.EmprestimoRepository;

@Service
public class EmprestimoService {
    
    @Autowired
    EmprestimoRepository emprestimoRepository;

    @Autowired
    UsuarioService usuarioS;

    @Autowired
    LivroService livroS;
    
    
    public List<Emprestimo> buscaEmprestimos(){
        return emprestimoRepository.findAll();
    }
    
    public Emprestimo buscarPorId(Long id){
        return emprestimoRepository.findById(id).get();
    }

    public List<Emprestimo> buscarPorUsuarioId(Long idUser){
        return emprestimoRepository.findByUsuarioId(usuarioS.buscarPorId(idUser).getId());
    }
    public List<Emprestimo> buscarPorUsuarioNome(String nome){
        return emprestimoRepository.findByUsuarioNomeContaining(nome);
    }

    public List<Emprestimo> buscarPorLivroId(Long idLivro){
        return emprestimoRepository.findByLivroId(livroS.buscarLivroId(idLivro).get().getId());
    }
    public List<Emprestimo> buscarPorLivro(String titulo){
        return emprestimoRepository.findByLivroTituloContaining(titulo);
    }

    public Emprestimo editarEmprestimo(Emprestimo emp){
        Emprestimo emprestimo = buscarPorId(emp.getId());

        emprestimo.setDtDevolucao(emp.getDtDevolucao());
        emprestimo.setStatus(emp.getStatus());
        emprestimo.setUsuario(emp.getUsuario());

        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo deletaEmprestimo(Emprestimo emp){

        emp.setStatus(StatusEmpretimo.DELETADO);
        
        return emprestimoRepository.save(emp);
    }

    public Emprestimo novoEmprestimo(Emprestimo emp){

        LocalDate dataInicial = LocalDate.now();
        emp.setDtEmprestimo(dataInicial);
        emp.setDtDevolucao(dataInicial.plusDays(7));
        emp.setStatus(StatusEmpretimo.ATIVO);

        return emprestimoRepository.save(emp);
    }

    public Boolean verificaAtraso(Usuario user){
        List<Emprestimo> emps = buscarPorUsuarioId(user.getId());
        
        for (Emprestimo emprestimo : emps) {
                if (emprestimo.getStatus().equals(StatusEmpretimo.ATRASADO)) {
                    return true;            
                }
        }
        return false;

    }

    public Boolean deletarEmprestimo(Long id){
        Emprestimo emp = buscarPorId(id);
        emp.setStatus(StatusEmpretimo.DELETADO);
        emp = emprestimoRepository.save(emp);
        if (emp.getStatus().equals(StatusEmpretimo.DELETADO)) {
            return true;
        }else{
            return false;
        }

    }

}
