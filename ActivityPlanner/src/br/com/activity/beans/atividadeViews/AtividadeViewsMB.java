package br.com.activity.beans.atividadeViews;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.activity.atividade.entidade.Atividade;
import br.com.activity.atividade.to.AtividadeTO;
import br.com.activity.facade.ActivityFacade;

@ManagedBean
@ViewScoped
public class AtividadeViewsMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 395657371811710833L;
	
	private List<AtividadeTO> listAtividadeTO;
	
	private AtividadeTO selectedAtividadeTO;
	
	public AtividadeViewsMB(){
		listAtividadeTO = new ArrayList<AtividadeTO>();
		loadBean();
	}

	
	public void loadBean(){
		try {
			if (ActivityFacade.getInstance().listAtividades().size() > 0){
				for (Atividade atividade : ActivityFacade.getInstance().listAtividades()) {
					listAtividadeTO.add(new AtividadeTO(atividade));
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public List<AtividadeTO> getListAtividadeTO() {
		return listAtividadeTO;
	}

	public void setListAtividadeTO(List<AtividadeTO> listAtividadeTO) {
		this.listAtividadeTO = listAtividadeTO;
	}

	public AtividadeTO getSelectedAtividadeTO() {
		return selectedAtividadeTO;
	}

	public void setSelectedAtividadeTO(AtividadeTO selectedAtividadeTO) {
		this.selectedAtividadeTO = selectedAtividadeTO;
	}
	

}
