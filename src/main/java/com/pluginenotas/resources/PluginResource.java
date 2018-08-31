package com.pluginenotas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pluginenotas.domain.Plugin;
import com.pluginenotas.services.PluginService;

@RestController
@RequestMapping(value="/plugins")
public class PluginResource {
	
	@Autowired
	private PluginService service;
	
	public ResponseEntity<Plugin> find(@PathVariable Integer id){
		Plugin obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

}
