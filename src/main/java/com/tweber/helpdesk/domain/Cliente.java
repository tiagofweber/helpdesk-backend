package com.tweber.helpdesk.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Pessoa {

    private List<Chamado> chamados = new ArrayList<>();
}
