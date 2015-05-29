package br.com.activity.grupo.entidade;

import java.util.Date;

import javax.persistence.Column;

public class Grupo {
	
	@Column(name="ID")
	long id;
	
	@Column(name="NOME")
	String nome;
	
	@Column(name="DESCRICAO")
	String descricao;
	
	@Column(name="CRIADO_EM")
	private Date criadoEm;

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

	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}
	
	
	

}
