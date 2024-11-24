package com.elberjsn.biblioteca.service.DAO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.elberjsn.biblioteca.model.Emprestimo;
import com.elberjsn.biblioteca.model.StatusEmpretimo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class EmprestimoDAO implements  Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDate dtEmprestimo;
    private LocalDate dtDevolucao;
    private StatusEmpretimo status;
    private Long usuarioId;
    private String usuarioNome;
    private Long livroId;
    private String livroTitulo;

    public EmprestimoDAO(Emprestimo emp){
        id= emp.getId();
        dtDevolucao = emp.getDtDevolucao();
        dtEmprestimo = emp.getDtEmprestimo();
        status=emp.getStatus();
        usuarioId = emp.getUsuario().getId();
        usuarioNome = emp.getUsuario().getNome();
        livroId = emp.getLivro().getId();
        livroTitulo = emp.getLivro().getTitulo();

    }

    public List<EmprestimoDAO> emprestimoDAOs(List<Emprestimo> emps){
        List<EmprestimoDAO> empDAO = new ArrayList<>();
        
        for (Emprestimo emp : emps) {
            empDAO.add(new EmprestimoDAO(emp));
        }

        return empDAO;
    }
}
