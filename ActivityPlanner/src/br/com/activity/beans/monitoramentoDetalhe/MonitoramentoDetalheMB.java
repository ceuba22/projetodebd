package br.com.activity.beans.monitoramentoDetalhe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.activity.atividade.dao.AtividadeDAO;
import br.com.activity.atividade.to.AtividadeAlocadaTO;
import br.com.activity.facade.ActivityFacade;
import br.com.activity.projetos.entidade.Projeto;
import br.com.activity.projetos.to.ProjetoTO;

@ManagedBean
@ViewScoped
public class MonitoramentoDetalheMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7700350398011631393L;

	private List<ProjetoTO> listProjetoTO;
	
	private long projetoId;
	
	private List<AtividadeAlocadaTO> listAtividadeAlocadaTO;
	
	

	public MonitoramentoDetalheMB(){
		loadListProjetos();
	}

	public void loadListProjetos(){
		listProjetoTO = new ArrayList<ProjetoTO>();
		try {
			if(ActivityFacade.getInstance().listProjetos().size() > 0){

				for (Projeto projetos : ActivityFacade.getInstance().listProjetos()) {
					listProjetoTO.add(new ProjetoTO(projetos));
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void loadProjeto() throws ClassNotFoundException{
		listAtividadeAlocadaTO = new ArrayList<AtividadeAlocadaTO>();
		try {
			List<AtividadeAlocadaTO> atividade = AtividadeDAO.getInstance().listAtividadesAlocadas(projetoId);
			if(atividade != null && atividade.size() > 0){
				listAtividadeAlocadaTO.addAll(atividade) ;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<ProjetoTO> getListProjetoTO() {
		return listProjetoTO;
	}

	public void setListProjetoTO(List<ProjetoTO> listProjetoTO) {
		this.listProjetoTO = listProjetoTO;
	}
	

	public long getProjetoId() {
		return projetoId;
	}

	public void setProjetoId(long projetoId) {
		this.projetoId = projetoId;
	}

	public List<AtividadeAlocadaTO> getListAtividadeAlocadaTO() {
		return listAtividadeAlocadaTO;
	}

	public void setListAtividadeAlocadaTO(
			List<AtividadeAlocadaTO> listAtividadeAlocadaTO) {
		this.listAtividadeAlocadaTO = listAtividadeAlocadaTO;
	}

}
