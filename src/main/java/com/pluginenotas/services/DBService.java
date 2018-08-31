package com.pluginenotas.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluginenotas.domain.Cliente;
import com.pluginenotas.domain.Empresa;
import com.pluginenotas.domain.Plugin;
import com.pluginenotas.repositories.ClienteRepository;
import com.pluginenotas.repositories.EmpresaRepository;
import com.pluginenotas.repositories.PluginRepository;

@Service
public class DBService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private PluginRepository pluginRepository;
	
	public void instantiateDatabase() {

	Cliente cli1 = new Cliente(null, "Cliente de Teste numero 1", "localhost","unosol","#prudencia5!","db_uc_tantrix");
	Cliente cli2 = new Cliente(null, "Cliente de Teste numero 2", "localhost","unosol","#prudencia5!","db_uc_littlecherry");
	
	Empresa emp1 = new Empresa();
	emp1.setNomeFantasia("Fantasia Empresa 1");
	emp1.setId(null);
	emp1.setCliente(cli1);
	
	Empresa emp2 = new Empresa();
	emp2.setNomeFantasia("Fantasia Empresa 2");
	emp2.setCliente(cli1);
	
	
	cli1.getEmpresas().addAll(Arrays.asList(emp1, emp2));
	
	clienteRepository.saveAll(Arrays.asList(cli1, cli2));
	empresaRepository.saveAll(Arrays.asList(emp1));

	Plugin plg1 = new Plugin(null,"Plugin 1","Sscript ativaçao plugin 1");
	Plugin plg2 = new Plugin(null,"Plugin 2","Sscript ativaçao plugin 2");
	Plugin plg3 = new Plugin(null,"Plugin 3","Sscript ativaçao plugin 3");
	
	pluginRepository.saveAll(Arrays.asList(plg1, plg2, plg3));
	
	}

}
