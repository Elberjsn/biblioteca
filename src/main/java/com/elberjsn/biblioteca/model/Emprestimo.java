package com.elberjsn.biblioteca.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Emprestimo implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dtEmprestimo;
    private LocalDate dtDevolucao;
    private StatusEmpretimo status;

    @ManyToOne
    @JoinColumn(name = "usuario_emprestimo")
    @NotNull(message ="O campo não pode ser Vazio!")
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name = "livro_emprestimo")
    @NotNull(message ="O campo não pode ser Vazio!")
    private Livro livro;
    
}
