package br.com.activity.grupo.to;

import java.util.Date;

import br.com.activity.grupo.entidade.Grupo;

public class GrupoTO {
	
	private long id;
	
	private String nome;
	
	private String descricao;
	
	private Date criadoEm;
	
	public GrupoTO(){
		
	}
	
	public GrupoTO(Grupo grupo){
		this.id = grupo.getId();
		this.nome = grupo.getNome();
		this.descricao = grupo.getDescricao();
		this.criadoEm = grupo.getCriadoEm();
	}
	
	
	public Grupo toVO(){
		Grupo grupo = new Grupo();
		grupo.setNome(this.getNome());
		grupo.setDescricao(this.getDescricao());
		grupo.setCriadoEm(new Date());
		
		return grupo;
		
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
