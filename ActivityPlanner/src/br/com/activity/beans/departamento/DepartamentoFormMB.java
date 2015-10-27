package br.com.activity.beans.departamento;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.com.activity.grupo.dao.GrupoDAO;
import br.com.activity.grupo.entidade.Grupo;
import br.com.activity.grupo.to.GrupoTO;

@ManagedBean
@ViewScoped
public class DepartamentoFormMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1785452086424927055L;
	
	private GrupoTO grupoTO;
	
	public DepartamentoFormMB(){
		grupoTO = new GrupoTO();
		
	}
	
	public void insertDepartamento(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		boolean hasError = false;
		RequestContext requestContext = RequestContext.getCurrentInstance();

		if(grupoTO.getNome().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Nome do Departamento está vazio."));
			requestContext.update("formPrincipal:messageUsuario");
		}

		if(!hasError){
			Grupo grupo = grupoTO.toVO();
			try {
				GrupoDAO.getInstance().insertGrupo(grupo);
				grupoTO = new GrupoTO();
				requestContext.update("formPrincipal");
				requestContext.execute("PF('criarDepartamentoDialog').hide()");
			} catch (Exception e) {
			}

		}
	}

	public GrupoTO getGrupoTO() {
		return grupoTO;
	}

	public void setGrupoTO(GrupoTO grupoTO) {
		this.grupoTO = grupoTO;
	}

}
