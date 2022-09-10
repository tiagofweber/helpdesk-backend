package com.tweber.helpdesk.services;

import com.tweber.helpdesk.domain.Pessoa;
import com.tweber.helpdesk.domain.Tecnico;
import com.tweber.helpdesk.domain.dtos.TecnicoDTO;
import com.tweber.helpdesk.exceptions.ObjectNotFoundException;
import com.tweber.helpdesk.repositories.PessoaRepository;
import com.tweber.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Tecnico findById(Long id) {
        Optional<Tecnico> tecnico = repository.findById(id);
        return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(null);
        tecnicoDTO.setSenha(encoder.encode(tecnicoDTO.getSenha()));
        validaPorCpfEEmail(tecnicoDTO);
        Tecnico tecnico = new Tecnico(tecnicoDTO);
        return repository.save(tecnico);
    }

    public Tecnico update(Long id, TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(id);
        Tecnico oldTecnico = findById(id);
        validaPorCpfEEmail(tecnicoDTO);
        oldTecnico = new Tecnico(tecnicoDTO);
        return repository.save(oldTecnico);
    }

    public void delete(Long id) {
        Tecnico tecnico = findById(id);

        if (!tecnico.getChamados().isEmpty()) {
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }

        repository.deleteById(id);
    }

    private void validaPorCpfEEmail(TecnicoDTO tecnicoDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
        if (pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), tecnicoDTO.getId())) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        pessoa = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
        if (pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), tecnicoDTO.getId())) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }
}
