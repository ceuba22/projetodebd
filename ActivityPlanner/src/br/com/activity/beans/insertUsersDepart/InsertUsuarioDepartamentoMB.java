package br.com.activity.beans.insertUsersDepart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.activity.grupo.dao.GrupoDAO;
import br.com.activity.grupo.entidade.Grupo;
import br.com.activity.grupo.to.GrupoTO;


@ManagedBean
@ViewScoped
public class InsertUsuarioDepartamentoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6065531722255415018L;

	private List<GrupoTO> listGrupoTO;

	public InsertUsuarioDepartamentoMB() throws ClassNotFoundException{
		listGrupoTO = new ArrayList<GrupoTO>();
		if(GrupoDAO.getInstance().listGrupos().size() > 0){
			for (Grupo grupo : GrupoDAO.getInstance().listGrupos()) {
				listGrupoTO.add(new GrupoTO(grupo));
			}
		}

	}

	public List<GrupoTO> getListGrupoTO() {
		return listGrupoTO;
	}

	public void setListGrupoTO(List<GrupoTO> listGrupoTO) {
		this.listGrupoTO = listGrupoTO;
	}

}
