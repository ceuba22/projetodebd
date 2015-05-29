package br.com.activity.tag.entidade;

import javax.persistence.Column;

public class Tag {
	
	@Column(name="ID")
	long id;
	
	@Column(name="NOME")
	String nome;
	
	@Column(name="DESCRICAO")
	String descricao;

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
	
	
	

}
