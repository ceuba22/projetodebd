package br.com.activity.beans.project;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.activity.atividade.entidade.Atividade;
import br.com.activity.atividadeAlocada.to.AtividadeAlocadaTO;
import br.com.activity.grupo.entidade.Grupo;
import br.com.activity.users.to.UsersTO;
import br.com.activity.util.PrioridadeTipo;
import br.com.activity.util.StatusTipo;

@ManagedBean
@SessionScoped
public class ProjetoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4089861191778005390L;

	private long id;

	private String nome;

	private Date criadoEm;

	private String descricao;

	private PrioridadeTipo prioridade;

	private String prioridadeString;

	private StatusTipo status;

	private UsersTO criadoPor;

	private List<Grupo> listGrupo;

	private List<Atividade> listAtividade;
	
	private List<AtividadeAlocadaTO> listAtividadeAlocada;
	
	private static ProjetoMB instance;
	
	public static ProjetoMB getInstance() {
		if (instance == null) {
			instance = new ProjetoMB();
		}
		return instance;
	}


	public ProjetoMB(){

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

	public PrioridadeTipo getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(PrioridadeTipo prioridade) {
		this.prioridade = prioridade;
	}

	public String getPrioridadeString() {
		return prioridadeString;
	}

	public void setPrioridadeString(String prioridadeString) {
		this.prioridadeString = prioridadeString;
	}

	public StatusTipo getStatus() {
		return status;
	}

	public void setStatus(StatusTipo status) {
		this.status = status;
	}

	public UsersTO getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(UsersTO criadoPor) {
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

}
