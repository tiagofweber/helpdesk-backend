package com.tweber.helpdesk.controllers;

import com.tweber.helpdesk.domain.Tecnico;
import com.tweber.helpdesk.domain.dtos.TecnicoDTO;
import com.tweber.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Long id) {
        Tecnico tecnico = service.findById(id);
        return ResponseEntity.ok(new TecnicoDTO(tecnico));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<Tecnico> tecnicos = service.findAll();
        List<TecnicoDTO> tecnicosDTO = tecnicos.stream().map(TecnicoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(tecnicosDTO);
    }
}
