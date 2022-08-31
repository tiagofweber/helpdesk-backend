package com.tweber.helpdesk;

import com.tweber.helpdesk.domain.Chamado;
import com.tweber.helpdesk.domain.Cliente;
import com.tweber.helpdesk.domain.Tecnico;
import com.tweber.helpdesk.domain.enums.Perfil;
import com.tweber.helpdesk.domain.enums.Prioridade;
import com.tweber.helpdesk.domain.enums.Status;
import com.tweber.helpdesk.repositories.ChamadoRepository;
import com.tweber.helpdesk.repositories.ClienteRepository;
import com.tweber.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null, "Tiago Weber", "63653230268", "tiago@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Linus Torvalds", "80527954780", "torvalds@mail.com", "123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);

		tecnicoRepository.saveAll(List.of(tec1));
		clienteRepository.saveAll(List.of(cli1));
		chamadoRepository.saveAll(List.of(c1));
	}
}
