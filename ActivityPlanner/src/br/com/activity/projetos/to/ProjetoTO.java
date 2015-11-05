package br.com.activity.projetos.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.activity.atividade.entidade.Atividade;
import br.com.activity.atividade.to.AtividadeAlocadaTO;
import br.com.activity.facade.ActivityFacade;
import br.com.activity.grupo.entidade.Grupo;
import br.com.activity.projetos.entidade.Projeto;
import br.com.activity.users.to.UsersTO;
import br.com.activity.util.ActivityUtil;
import br.com.activity.util.PrioridadeTipo;
import br.com.activity.util.StatusTipo;

public class ProjetoTO {
	
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
	
	private List<AtividadeAlocadaTO> listAtividadesAlocadas;
	
	private Date prazoDeConclusao;
	
	private String prazoDeConclusaoString;
	
	public ProjetoTO(){
		
	}
	
	public ProjetoTO(Projeto projeto){
		this.id = projeto.getId();
		this.nome = projeto.getNome();
		this.descricao = projeto.getDescricao();
		this.prioridade = projeto.getPrioridade();
		this.criadoEm = projeto.getCriadoEm();
		this.status = projeto.getStatus(); 
		this.criadoPor = new UsersTO(projeto.getCriadoPor());
		this.listAtividade = projeto.getListAtividade();
		this.listGrupo = projeto.getListGrupo();
		this.listAtividadesAlocadas = projeto.getListAtividadeAlocada();
		this.prazoDeConclusao = projeto.getPrazoDeConclusao();
		
		
		
		
	}
	
	public Projeto toVO(){
		Projeto projeto = new Projeto();
		
		projeto.setNome(this.getNome());
		projeto.setDescricao(this.getDescricao());
		if(this.prioridadeString.equals("ALTA")){
			projeto.setPrioridade(PrioridadeTipo.ALTA);	
		}
		if(this.prioridadeString.equals("MEDIA")){
			projeto.setPrioridade(PrioridadeTipo.MEDIA);
		}
		if(this.prioridadeString.equals("BAIXA")){
			projeto.setPrioridade(PrioridadeTipo.BAIXA);
		}
		projeto.setStatus(this.getStatus());
		projeto.setCriadoEm(new Date());
		projeto.setListGrupo(new ArrayList<Grupo>());
		projeto.setListAtividade(new ArrayList<Atividade>());
		projeto.setListAtividadeAlocada(new ArrayList<AtividadeAlocadaTO>());
		try {
			projeto.setPrazoDeConclusao(ActivityUtil.getInstance().formataData(this.prazoDeConclusaoString));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			projeto.setCriadoPor(ActivityFacade.getInstance().getUsers(ActivityUtil.getInstance().getUsersTOLogado()));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return projeto;	
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

	public List<AtividadeAlocadaTO> getListAtividadesAlocadas() {
		return listAtividadesAlocadas;
	}

	public void setListAtividadesAlocadas(
			List<AtividadeAlocadaTO> listAtividadesAlocadas) {
		this.listAtividadesAlocadas = listAtividadesAlocadas;
	}

	public Date getPrazoDeConclusao() {
		return prazoDeConclusao;
	}

	public void setPrazoDeConclusao(Date prazoDeConclusao) {
		this.prazoDeConclusao = prazoDeConclusao;
	}

	public String getPrazoDeConclusaoString() {
		return prazoDeConclusaoString;
	}

	public void setPrazoDeConclusaoString(String prazoDeConclusaoString) {
		this.prazoDeConclusaoString = prazoDeConclusaoString;
	}
	
	

}



