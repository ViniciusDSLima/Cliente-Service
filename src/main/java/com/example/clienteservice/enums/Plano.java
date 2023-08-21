package com.example.clienteservice.enums;

import lombok.Data;

public enum Plano {
    VIP(1, "ROLE_VIP"),
    COMUM(2, "ROLE_COMUM"),
    PREMIUM(3, "ROLE_PREMIUM");

    private Integer codigo;
    private String descricao;

    Plano(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
