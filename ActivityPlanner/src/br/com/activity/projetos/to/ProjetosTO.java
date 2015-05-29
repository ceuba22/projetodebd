package br.com.activity.projetos.to;

import java.util.Date;

import br.com.activity.projetos.entidade.Projetos;

public class ProjetosTO {
	
	private long id;
	
	private String nome;
	
	private Date criadoEm;
	
	private String descricao;
	
	private String prioridade;
	
	private String status;
	
	//private int criadoPor;
	
	public ProjetosTO(){
		
	}
	
	public ProjetosTO(Projetos projetos){
		//this.id = getId();
		this.nome = getNome();
		this.descricao = getDescricao();
		this.prioridade = getPrioridade();
		this.criadoEm = new Date();
		this.status = getStatus();
		//this.criadoPor = new ProjetosTO();
		
		
		
		
	}
	
	public Projetos toVO(){
		Projetos projetos = new Projetos();
		projetos.setNome(this.getNome());
		projetos.setDescricao(this.getDescricao());
		projetos.setPrioridade(this.getPrioridade());
		projetos.setStatus(this.getStatus());
		projetos.setCriadoEm(new Date());
		//projetos.setCriadoPor(new Projetos());
		return projetos;	
	}

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

	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
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
	
	
	
	
	

}



