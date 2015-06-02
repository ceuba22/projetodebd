package br.com.activity.atividade.entidade;

import java.util.Date;

import javax.persistence.Column;

import br.com.activity.tag.entidade.Tag;

public class Atividade {
	
	
	@Column(name="ID")
	long id;
	
	@Column(name="NOME")
	String nome;
	
	@Column(name="DESCRICAO")
	String descricao;
	
	@Column(name="CRIADO_EM")
	private Date criadoEm;
	
	@Column(name="PESO")
	int peso;
	
	@Column(name="TAG_ID")
	Tag tag;
	
	@Column(name="TEMPO_EXECUCAO_PREVISTO")
	long tempoExecucao;
	
	@Column(name="TIPO_EXECUCAO")
	String tipoExecucao;

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

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public long getTempoExecucao() {
		return tempoExecucao;
	}

	public void setTempoExecucao(long tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public String getTipoExecucao() {
		return tipoExecucao;
	}

	public void setTipoExecucao(String tipoExecucao) {
		this.tipoExecucao = tipoExecucao;
	}
	

}
