package com.pluginenotas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pluginenotas.domain.Plugin;

@Repository
public interface PluginRepository extends JpaRepository<Plugin, Integer>{

}
