package com.pluginenotas.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pluginenotas.domain.Cliente;
import com.pluginenotas.services.ClienteService;
import com.pluginenotas.services.EnotasService;

@RestController
@RequestMapping(value="/enotas")
public class EnotasResource {
	
	@Autowired 
	private EnotasService enotas;
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/incluirempresa/{id}")
	public String find(@PathVariable Integer id) {
		return enotas.buildJsonEmpresa(id);
				
	}
	
	@RequestMapping(value="/cliente/{id}")
	public String cadastrarCliente(@PathVariable Integer id) {
		return enotas.buildJsonEmpresa(clienteService.find(id).getId());
	}
	
	@RequestMapping(value="/inserir", method=RequestMethod.GET)
	public String cadastrar(
			@RequestParam(value="host",defaultValue="localhost") String host,
			@RequestParam(value="dbName",defaultValue="db_uc_demo") String dbName,
			@RequestParam(value="user",defaultValue="root") String user,
			@RequestParam(value="pass",defaultValue="root") String pass,
			@RequestParam(value="port",defaultValue="3306") String port,
			@RequestParam(value="id",defaultValue = "1") Integer id) {
		return enotas.buildJsonEmpresa(host, dbName, user, pass, port, id);
	}
	
	

}
