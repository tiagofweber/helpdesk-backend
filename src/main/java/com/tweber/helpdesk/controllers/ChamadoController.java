package com.tweber.helpdesk.controllers;

import com.tweber.helpdesk.domain.Chamado;
import com.tweber.helpdesk.domain.dtos.ChamadoDTO;
import com.tweber.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Long id) {
        Chamado chamado = service.findById(id);
        return ResponseEntity.ok(new ChamadoDTO(chamado));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll() {
        List<Chamado> chamados = service.findAll();
        List<ChamadoDTO> chamadosDTO = chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(chamadosDTO);
    }
}
