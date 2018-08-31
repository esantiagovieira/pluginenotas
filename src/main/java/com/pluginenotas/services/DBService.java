package com.pluginenotas.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluginenotas.domain.Cliente;
import com.pluginenotas.domain.Empresa;
import com.pluginenotas.repositories.ClienteRepository;
import com.pluginenotas.repositories.EmpresaRepository;

@Service
public class DBService {
	
	@Autowired
	private ClienteRepository clienteRepositorty;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	public void instantiateDatabase() {

		
	Cliente cli = new Cliente();
	cli.setNome("Empresa1");
	cli.setDbHost("localhost");
	cli.setDbName("db_uc_tantrix");
	cli.setDbUser("unosol");
	cli.setDbPass("%23prudencia5!");
	cli.setId(null);
	
	Empresa emp = new Empresa();
	emp.setNomeFantasia("Nome Fantasia");
	emp.setId(null);
	emp.setCliente(cli);
	
	cli.getEmpresas().addAll(Arrays.asList(emp));
	
	clienteRepositorty.saveAll(Arrays.asList(cli));
	empresaRepository.saveAll(Arrays.asList(emp));

	
	}

}
