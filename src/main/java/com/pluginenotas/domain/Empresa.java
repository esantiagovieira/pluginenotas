package com.pluginenotas.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Empresa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer codigoIbgeUf;
	private Integer codigoIbgeCidade;
	private String pais;
	private String uf;
	private String cidade;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String cnpj;
	private String inscricaoMunicipal;
	private String inscricaoEstadual;
	private String razaoSocial;
	private String nomeFantasia;
	private boolean optanteSimplesNacional;
	private String email;
	private String telefoneComercial;
	private boolean incentivadorCultural;
	private String regimeEspecialTributacao;
	private String codigoServicoMunicipal;
	private String itemListaServicoLC116;
	private String cnae;
	private Double aliquotaIss;
	private String descricaoServico;
	private boolean enviarEmailCliente;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	public Empresa() {}

	public Empresa(Integer id, Integer codigoIbgeUf, Integer codigoIbgeCidade, String pais, String uf, String cidade,
			String logradouro, String numero, String complemento, String bairro, String cep, String cnpj,
			String inscricaoMunicipal, String inscricaoEstadual, String razaoSocial, String nomeFantasia,
			boolean optanteSimplesNacional, String email, String telefoneComercial, boolean incentivadorCultural,
			String regimeEspecialTributacao, String codigoServicoMunicipal, String itemListaServicoLC116, String cnae,
			Double aliquotaIss, String descricaoServico, boolean enviarEmailCliente, Cliente cliente) {
		super();
		this.id = id;
		this.codigoIbgeUf = codigoIbgeUf;
		this.codigoIbgeCidade = codigoIbgeCidade;
		this.pais = pais;
		this.uf = uf;
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cnpj = cnpj;
		this.inscricaoMunicipal = inscricaoMunicipal;
		this.inscricaoEstadual = inscricaoEstadual;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.optanteSimplesNacional = optanteSimplesNacional;
		this.email = email;
		this.telefoneComercial = telefoneComercial;
		this.incentivadorCultural = incentivadorCultural;
		this.regimeEspecialTributacao = regimeEspecialTributacao;
		this.codigoServicoMunicipal = codigoServicoMunicipal;
		this.itemListaServicoLC116 = itemListaServicoLC116;
		this.cnae = cnae;
		this.aliquotaIss = aliquotaIss;
		this.descricaoServico = descricaoServico;
		this.enviarEmailCliente = enviarEmailCliente;
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigoIbgeUf() {
		return codigoIbgeUf;
	}

	public void setCodigoIbgeUf(Integer codigoIbgeUf) {
		this.codigoIbgeUf = codigoIbgeUf;
	}

	public Integer getCodigoIbgeCidade() {
		return codigoIbgeCidade;
	}

	public void setCodigoIbgeCidade(Integer codigoIbgeCidade) {
		this.codigoIbgeCidade = codigoIbgeCidade;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public boolean isOptanteSimplesNacional() {
		return optanteSimplesNacional;
	}

	public void setOptanteSimplesNacional(boolean optanteSimplesNacional) {
		this.optanteSimplesNacional = optanteSimplesNacional;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefoneComercial() {
		return telefoneComercial;
	}

	public void setTelefoneComercial(String telefoneComercial) {
		this.telefoneComercial = telefoneComercial;
	}

	public boolean isIncentivadorCultural() {
		return incentivadorCultural;
	}

	public void setIncentivadorCultural(boolean incentivadorCultural) {
		this.incentivadorCultural = incentivadorCultural;
	}

	public String getRegimeEspecialTributacao() {
		return regimeEspecialTributacao;
	}

	public void setRegimeEspecialTributacao(String regimeEspecialTributacao) {
		this.regimeEspecialTributacao = regimeEspecialTributacao;
	}

	public String getCodigoServicoMunicipal() {
		return codigoServicoMunicipal;
	}

	public void setCodigoServicoMunicipal(String codigoServicoMunicipal) {
		this.codigoServicoMunicipal = codigoServicoMunicipal;
	}

	public String getItemListaServicoLC116() {
		return itemListaServicoLC116;
	}

	public void setItemListaServicoLC116(String itemListaServicoLC116) {
		this.itemListaServicoLC116 = itemListaServicoLC116;
	}

	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}

	public Double getAliquotaIss() {
		return aliquotaIss;
	}

	public void setAliquotaIss(Double aliquotaIss) {
		this.aliquotaIss = aliquotaIss;
	}

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	public boolean isEnviarEmailCliente() {
		return enviarEmailCliente;
	}

	public void setEnviarEmailCliente(boolean enviarEmailCliente) {
		this.enviarEmailCliente = enviarEmailCliente;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
