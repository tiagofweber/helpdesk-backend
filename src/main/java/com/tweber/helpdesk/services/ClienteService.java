package com.tweber.helpdesk.services;

import com.tweber.helpdesk.domain.Cliente;
import com.tweber.helpdesk.domain.Pessoa;
import com.tweber.helpdesk.domain.dtos.ClienteDTO;
import com.tweber.helpdesk.exceptions.ObjectNotFoundException;
import com.tweber.helpdesk.repositories.ClienteRepository;
import com.tweber.helpdesk.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente findById(Long id) {
        Optional<Cliente> cliente = repository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO clienteDto) {
        clienteDto.setId(null);
        clienteDto.setSenha(encoder.encode(clienteDto.getSenha()));
        validaPorCpfEEmail(clienteDto);
        Cliente cliente = new Cliente(clienteDto);
        return repository.save(cliente);
    }

    public Cliente update(Long id, ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        Cliente oldCliente = findById(id);
        validaPorCpfEEmail(clienteDTO);
        oldCliente = new Cliente(clienteDTO);
        return repository.save(oldCliente);
    }

    public void delete(Long id) {
        Cliente cliente = findById(id);

        if (!cliente.getChamados().isEmpty()) {
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
        }

        repository.deleteById(id);
    }

    private void validaPorCpfEEmail(ClienteDTO clienteDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(clienteDTO.getCpf());
        if (pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), clienteDTO.getId())) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        pessoa = pessoaRepository.findByEmail(clienteDTO.getEmail());
        if (pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), clienteDTO.getId())) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }
}