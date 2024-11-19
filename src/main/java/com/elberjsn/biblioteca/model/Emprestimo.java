package com.elberjsn.biblioteca.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter

public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dtEmprestimo;
    private Date dtDevolucao;
    private String status;

    @ManyToOne
    @JoinColumn(name = "usuario_emprestimo")
    private Usuario usuario_emprestimo;


    @ManyToOne
    @JoinColumn(name = "emprestimo_livro")
    private Livro emprestimo_livro;
    
}
