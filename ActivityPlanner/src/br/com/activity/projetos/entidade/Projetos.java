package br.com.activity.projetos.entidade;

import java.util.Date;

import javax.persistence.Column;

public class Projetos {
	
	@Column(name="ID")
	long id;
	
	@Column(name="NOME")
	String nome;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@Column(name="PRIORIDADE")
	private String prioridade;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="CRIADO_EM")
	private Date criadoEm;
	
	//@Column(name="CRIADO_POR")
	//private Projetos criadoPor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}

	//public Projetos getCriadoPor() {
		//return criadoPor;
	//}

	//public void setCriadoPor(Projetos criadoPor) {
		//this.criadoPor = criadoPor;
	//}
	
	
	
	

}
