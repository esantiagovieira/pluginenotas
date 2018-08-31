package com.pluginenotas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pluginenotas.domain.Empresa;
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

}
