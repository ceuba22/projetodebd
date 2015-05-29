package br.com.activity.tag.to;

import br.com.activity.tag.entidade.Tag;

public class TagTO {
	
	
	private long id;
	
	private String nome;
	
	private String descricao;
	
	public TagTO(Tag tag){
		//this.id = getId();
		this.nome = getNome();
		this.descricao = getDescricao();
	}
	
	
	public Tag toVO(){
		Tag tag = new Tag();
		tag.setDescricao(this.getDescricao());
		tag.setNome(this.getNome());
		
		return tag;
		
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
