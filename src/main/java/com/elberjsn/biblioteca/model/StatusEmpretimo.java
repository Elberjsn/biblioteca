package com.elberjsn.biblioteca.model;

import lombok.Getter;

@Getter
public enum StatusEmpretimo {

    ATIVO(0),
    CONCLUIDO(1),
    ATRASADO(2),
    DELETADO(3);

    private Integer numero;

    StatusEmpretimo(Integer num){
        this.numero = num;
    }
}
