package br.com.activity.projetos.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import br.com.activity.atividade.entidade.Atividade;
import br.com.activity.atividadeAlocada.to.AtividadeAlocadaTO;
import br.com.activity.grupo.entidade.Grupo;
import br.com.activity.users.entidade.Users;
import br.com.activity.util.PrioridadeTipo;
import br.com.activity.util.StatusTipo;

public class Projeto {
	
	@Column(name="ID")
	long id;
	
	@Column(name="NOME")
	String nome;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@Column(name="PRIORIDADE")
	private PrioridadeTipo prioridade;
	
	@Column(name="STATUS")
	private StatusTipo status;
	
	@Column(name="CRIADO_EM")
	private Date criadoEm;
	
	@Column(name="CRIADO_POR")
	private Users criadoPor;
	
	private List<Grupo> listGrupo;
	
	private List<Atividade> listAtividade;
	
	private List<AtividadeAlocadaTO> listAtividadeAlocada;
	
	private Date prazoDeConclusao;

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

	public PrioridadeTipo getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(PrioridadeTipo prioridade) {
		this.prioridade = prioridade;
	}

	public StatusTipo getStatus() {
		return status;
	}

	public void setStatus(StatusTipo status) {
		this.status = status;
	}

	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Users getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(Users criadoPor) {
		this.criadoPor = criadoPor;
	}

	public List<Grupo> getListGrupo() {
		return listGrupo;
	}

	public void setListGrupo(List<Grupo> listGrupo) {
		this.listGrupo = listGrupo;
	}

	public List<Atividade> getListAtividade() {
		return listAtividade;
	}

	public void setListAtividade(List<Atividade> listAtividade) {
		this.listAtividade = listAtividade;
	}

	public List<AtividadeAlocadaTO> getListAtividadeAlocada() {
		return listAtividadeAlocada;
	}

	public void setListAtividadeAlocada(
			List<AtividadeAlocadaTO> listAtividadeAlocada) {
		this.listAtividadeAlocada = listAtividadeAlocada;
	}

	public Date getPrazoDeConclusao() {
		return prazoDeConclusao;
	}

	public void setPrazoDeConclusao(Date prazoDeConclusao) {
		this.prazoDeConclusao = prazoDeConclusao;
	}
	
	
	
	

}
