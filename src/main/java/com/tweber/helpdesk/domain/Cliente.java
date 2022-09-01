package com.tweber.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tweber.helpdesk.domain.enums.Perfil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cliente extends Pessoa {

    public Cliente(Long id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        this.addPerfil(Perfil.CLIENTE);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();
}
