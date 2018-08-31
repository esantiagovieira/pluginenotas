package com.pluginenotas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluginenotas.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
}
