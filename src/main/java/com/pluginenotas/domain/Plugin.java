package com.pluginenotas.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Plugin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String nome;
	private String sqlQuery;
	
	public Plugin() {}
	
	public Plugin(Integer id, @NotNull String nome, String sqlQuery) {
		super();
		this.id = id;
		this.nome = nome;
		this.sqlQuery = sqlQuery;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}
	
}
