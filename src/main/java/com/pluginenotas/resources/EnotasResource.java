package com.pluginenotas.resources;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
		
	@RequestMapping(value="/cadastrarEmpresa", method=RequestMethod.POST)
	public ResponseEntity<String> cadastrarEmpresa(@RequestBody Map<String, Object> payload){
		String json = enotas.buildJsonEmpresa(payload);
		return ResponseEntity.status(HttpStatus.OK).body(enotas.enviarEmpresa(json));
	}
	
	@RequestMapping(value="/listarEmpresa/{cnpj}",method=RequestMethod.GET)
	public String buscaEmpresa(@PathVariable String cnpj) {
		return enotas.findByCNPJ(cnpj);
		
	}
}
