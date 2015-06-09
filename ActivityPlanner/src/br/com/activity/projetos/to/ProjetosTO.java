package br.com.activity.projetos.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.activity.atividade.entidade.Atividade;
import br.com.activity.facade.ActivityFacade;
import br.com.activity.grupo.entidade.Grupo;
import br.com.activity.projetos.entidade.Projetos;
import br.com.activity.users.to.UsersTO;
import br.com.activity.util.ActivityUtil;
import br.com.activity.util.PrioridadeTipo;
import br.com.activity.util.StatusTipo;

public class ProjetosTO {
	
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
	
	public ProjetosTO(){
		
	}
	
	public ProjetosTO(Projetos projetos){
		this.id = projetos.getId();
		this.nome = projetos.getNome();
		this.descricao = projetos.getDescricao();
		this.prioridade = projetos.getPrioridade();
		this.criadoEm = new Date();
		this.status = projetos.getStatus(); 
		this.criadoPor = new UsersTO(projetos.getCriadoPor());
		this.listAtividade = projetos.getListAtividade();
		this.listGrupo = projetos.getListGrupo();
		
		
		
		
	}
	
	public Projetos toVO(){
		Projetos projetos = new Projetos();
		
		projetos.setNome(this.getNome());
		projetos.setDescricao(this.getDescricao());
		if(this.prioridadeString.equals("ALTA")){
			projetos.setPrioridade(PrioridadeTipo.ALTA);	
		}
		if(this.prioridadeString.equals("MEDIA")){
			projetos.setPrioridade(PrioridadeTipo.MEDIA);
		}
		if(this.prioridadeString.equals("BAIXA")){
			projetos.setPrioridade(PrioridadeTipo.BAIXA);
		}
		projetos.setStatus(this.getStatus());
		projetos.setCriadoEm(new Date());
		projetos.setListGrupo(new ArrayList<Grupo>());
		projetos.setListAtividade(new ArrayList<Atividade>());
		
		try {
			projetos.setCriadoPor(ActivityFacade.getInstance().getUsers(ActivityUtil.getInstance().getUsersTOLogado()));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
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

	public UsersTO getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(UsersTO criadoPor) {
		this.criadoPor = criadoPor;
	}

	public String getPrioridadeString() {
		return prioridadeString;
	}

	public void setPrioridadeString(String prioridadeString) {
		this.prioridadeString = prioridadeString;
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
	
	

}



