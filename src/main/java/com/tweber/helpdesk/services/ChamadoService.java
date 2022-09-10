package com.tweber.helpdesk.services;

import com.tweber.helpdesk.domain.Chamado;
import com.tweber.helpdesk.domain.Cliente;
import com.tweber.helpdesk.domain.Tecnico;
import com.tweber.helpdesk.domain.dtos.ChamadoDTO;
import com.tweber.helpdesk.domain.enums.Prioridade;
import com.tweber.helpdesk.domain.enums.Status;
import com.tweber.helpdesk.exceptions.ObjectNotFoundException;
import com.tweber.helpdesk.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Long id) {
        Optional<Chamado> chamado = repository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException(String.format("Objeto não encontrado! ID: %s", id)));
    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }

    public Chamado create(ChamadoDTO chamadoDTO) {
        return repository.save(newChamado(chamadoDTO));
    }

    private Chamado newChamado(ChamadoDTO chamadoDTO) {
        Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico());
        Cliente cliente = clienteService.findById(chamadoDTO.getCliente());

        Chamado chamado = new Chamado();
        if (chamadoDTO.getId() != null) {
            chamado.setId(chamadoDTO.getId());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
        chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
        chamado.setTitulo(chamadoDTO.getTitulo());
        chamado.setObservacoes(chamadoDTO.getObservacoes());
        return chamado;
    }
}
