package com.tweber.helpdesk.domain.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Status {

    ABERTO(0, "ABERTO"),
    ANDAMENTO(1, "ANDAMENTO"),
    ENCERRADO(2, "ENCERRADO");

    private Integer codigo;
    private String descricao;

    private Status(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status toEnum(Integer codigo) {
        Optional<Status> status = Arrays.stream(Status.values()).filter(s -> s.getCodigo().equals(codigo)).findFirst();

        if (status.isEmpty())
            throw new IllegalArgumentException("Status inválido");

        return status.get();
    }
}
