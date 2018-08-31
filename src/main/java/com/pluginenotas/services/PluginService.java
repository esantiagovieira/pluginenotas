package com.pluginenotas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluginenotas.domain.Plugin;
import com.pluginenotas.repositories.PluginRepository;
import com.pluginenotas.services.exceptions.ObjectNotFoundException;

@Service
public class PluginService {
	
	@Autowired
	private PluginRepository repo;
	
	public Plugin find(Integer id) {
		Optional<Plugin> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado - Tipo:" + Plugin.class.getName()));
	}
	
}
