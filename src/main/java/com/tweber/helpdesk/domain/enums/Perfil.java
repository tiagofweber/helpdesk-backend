package com.tweber.helpdesk.domain.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Perfil {

    ADMIN(0, "ROLE_ADMIN"),
    CLIENTE(1, "ROLE_CLIENTE"),
    TECNICO(2, "ROLE_TECNICO");

    private Integer codigo;
    private String descricao;

    private Perfil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer codigo) {
        Optional<Perfil> perfil = Arrays.stream(Perfil.values()).filter(p -> p.getCodigo().equals(codigo)).findFirst();

        if (perfil.isEmpty())
            throw new IllegalArgumentException("Perfil inválido");

        return perfil.get();
    }
}
