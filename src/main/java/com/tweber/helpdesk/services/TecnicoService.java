package com.tweber.helpdesk.services;

import com.tweber.helpdesk.domain.Tecnico;
import com.tweber.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Long id) {
        Optional<Tecnico> tecnico = repository.findById(id);
        return tecnico.orElse(null);
    }
}
