package com.tweber.helpdesk.domain.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Prioridade {

    BAIXA(0, "BAIXA"),
    MEDIA(1, "MEDIA"),
    ALTA(2, "ALTA");

    private Integer codigo;
    private String descricao;

    private Prioridade(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Prioridade toEnum(Integer codigo) {
        Optional<Prioridade> prioridade = Arrays.stream(Prioridade.values()).filter(p -> p.getCodigo().equals(codigo)).findFirst();

        if (prioridade.isEmpty())
            throw new IllegalArgumentException("Prioridade inválida");

        return prioridade.get();
    }
}
