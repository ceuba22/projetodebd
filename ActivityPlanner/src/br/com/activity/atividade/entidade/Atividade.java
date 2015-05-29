package br.com.activity.atividade.entidade;

import java.util.Date;

import javax.persistence.Column;

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
	
	@Column(name="TEMPO_EXECUCAO")
	Date tempoExecucao;
	
	
	
	
	

}
