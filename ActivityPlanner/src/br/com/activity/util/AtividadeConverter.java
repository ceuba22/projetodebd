package br.com.activity.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityNotFoundException;

import br.com.activity.atividade.dao.AtividadeDAO;
import br.com.activity.atividade.entidade.Atividade;
import br.com.activity.grupo.to.GrupoTO;

@FacesConverter(value = "atividadeConverter")
public class AtividadeConverter  implements Converter {

	@Override
	public Atividade getAsObject(FacesContext facesContext, UIComponent uiComponent, String submittedValue) {
		Atividade atividade = new Atividade();
		if (submittedValue.trim().equals("")) {  
			return null;  
		} else {  
			try {  
//				int number = Integer.parseInt(submittedValue); 
				try {
					atividade = AtividadeDAO.getInstance().getAtividade((Long.valueOf(submittedValue)));
				} catch (EntityNotFoundException e) {
					atividade = new Atividade();
				} catch (Throwable e) {
					atividade = new Atividade();
				}

			} catch(NumberFormatException exception) {  
			}  
		}  
		return atividade;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		String result = "";
		if (value == null || value.equals("")) {  
			result =  "";  
		} else {
			if (value instanceof Atividade) {
				result = String.valueOf(((Atividade) value).getId());  
			} else if (value instanceof GrupoTO) {
				result = String.valueOf(((GrupoTO) value).getId());  	
			}
		}  
		return result;
	}
}
