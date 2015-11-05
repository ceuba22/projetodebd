package br.com.activity.atividade.to;

import java.util.Date;

import br.com.activity.atividade.entidade.Atividade;
import br.com.activity.projetos.entidade.Projeto;
import br.com.activity.projetos.to.ProjetoTO;
import br.com.activity.users.entidade.Users;
import br.com.activity.users.to.UsersTO;

public class AtividadeAlocadaTO {
	
	private long id;
	
	private UsersTO usersTO;
	
	private AtividadeTO atividadeTO;
	
	private ProjetoTO ProjetoTO;
	
	private Date dataInicioReal;
	
	private Date tempoExecucaoReal;
	
	public AtividadeAlocadaTO(){
		
		
	}
	
	public AtividadeAlocadaTO(long id, Users users, Atividade atividade, Projeto projetos, Date dataInicioReal, Date tempoExecucaoReal){
		this.setId(id);
		this.setUsersTO(new UsersTO(users));
		this.setAtividadeTO(new AtividadeTO(atividade));
		this.setProjetoTO(new ProjetoTO(projetos));
		this.setDataInicioReal(dataInicioReal);
		this.setTempoExecucaoReal(tempoExecucaoReal);
	}
	

	public UsersTO getUsersTO() {
		return usersTO;
	}

	public void setUsersTO(UsersTO usersTO) {
		this.usersTO = usersTO;
	}

	public AtividadeTO getAtividadeTO() {
		return atividadeTO;
	}

	public void setAtividadeTO(AtividadeTO atividadeTO) {
		this.atividadeTO = atividadeTO;
	}

	public ProjetoTO getProjetoTO() {
		return ProjetoTO;
	}

	public void setProjetoTO(ProjetoTO ProjetoTO) {
		this.ProjetoTO = ProjetoTO;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataInicioReal() {
		return dataInicioReal;
	}

	public void setDataInicioReal(Date dataInicioReal) {
		this.dataInicioReal = dataInicioReal;
	}

	public Date getTempoExecucaoReal() {
		return tempoExecucaoReal;
	}

	public void setTempoExecucaoReal(Date tempoExecucaoReal) {
		this.tempoExecucaoReal = tempoExecucaoReal;
	}

}
