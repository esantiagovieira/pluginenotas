package com.pluginenotas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pluginenotas.services.EnotasService;

@RestController
@RequestMapping(value="/enotas")
public class EnotasResource {
	
	@Autowired 
	private EnotasService enotas;
	
	@RequestMapping(value="/inserir", method=RequestMethod.GET)
	public ResponseEntity<String> cadastrar(
			@RequestParam(value="host",defaultValue="localhost") String host,
			@RequestParam(value="dbName",defaultValue="db_uc_demo") String dbName,
			@RequestParam(value="user",defaultValue="root") String user,
			@RequestParam(value="pass",defaultValue="root") String pass,
			@RequestParam(value="port",defaultValue="3306") String port,
			@RequestParam(value="id",defaultValue = "1") Integer id) {
		String json = enotas.buildJsonEmpresa(host, dbName, user, pass, port, id);
		return ResponseEntity.status(HttpStatus.OK).body(enotas.enviarEmpresa(json));
	}
	
}
