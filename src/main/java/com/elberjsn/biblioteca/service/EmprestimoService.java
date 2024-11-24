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
    
    
    public List<Emprestimo> buscaEmprestimos(){
        return emprestimoRepository.findAll();
    }
    
    public Emprestimo buscarPorId(Long id){
        return emprestimoRepository.findById(id).get();
    }

    public List<Emprestimo> buscarPorUsuario(Long idUser){
        return emprestimoRepository.findByUsuarioContaining(idUser);
    }

    public List<Emprestimo> buscarPorLivro(Long idLivro){
        return emprestimoRepository.findByLivroContaining(idLivro);
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
        List<Emprestimo> emps = buscarPorUsuario(user.getId());
        
        for (Emprestimo emprestimo : emps) {
                if (emprestimo.getStatus().equals(StatusEmpretimo.ATRASADO)) {
                    return true;            
                }
        }
        return false;

    }

}
