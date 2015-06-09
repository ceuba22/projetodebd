package br.com.activity.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityNotFoundException;

import br.com.activity.grupo.dao.GrupoDAO;
import br.com.activity.grupo.entidade.Grupo;
import br.com.activity.grupo.to.GrupoTO;

@FacesConverter(value = "grupoConverter")
public class GrupoConverter implements Converter {
	
	@Override
	public Grupo getAsObject(FacesContext facesContext, UIComponent uiComponent, String submittedValue) {
		Grupo grupo = new Grupo();
		if (submittedValue.trim().equals("")) {  
			return null;  
		} else {  
			try {  
//				int number = Integer.parseInt(submittedValue); 
				try {
					grupo = GrupoDAO.getInstance().getGrupo(Long.valueOf(submittedValue));
				} catch (EntityNotFoundException e) {
					grupo = new Grupo();
				} catch (Throwable e) {
					grupo = new Grupo();
				}

			} catch(NumberFormatException exception) {  
			}  
		}  
		return grupo;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		String result = "";
		if (value == null || value.equals("")) {  
			result =  "";  
		} else {
			if (value instanceof Grupo) {
				result = String.valueOf(((Grupo) value).getId());  
			} else if (value instanceof GrupoTO) {
				result = String.valueOf(((GrupoTO) value).getId());  	
			}
		}  
		return result;
	}


}
