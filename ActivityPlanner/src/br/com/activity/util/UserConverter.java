package br.com.activity.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityNotFoundException;

import br.com.activity.facade.ActivityFacade;
import br.com.activity.users.entidade.Users;
import br.com.activity.users.to.UsersTO;

@FacesConverter(value = "userConverter")
public class UserConverter  implements Converter {

	@Override
	public Users getAsObject(FacesContext facesContext, UIComponent uiComponent, String submittedValue) {
		Users users = new Users();
		if (submittedValue.trim().equals("")) {  
			return null;  
		} else {  
			try {  
				//				int number = Integer.parseInt(submittedValue); 
				try {
					users = ActivityFacade.getInstance().getUsers(Long.parseLong(submittedValue));
				} catch (EntityNotFoundException e) {
					users = new Users();
				} catch (Throwable e) {
					users = new Users();
				}

			} catch(NumberFormatException exception) {  
			}  
		}  
		return users;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		String result = "";
		if (value == null || value.equals("")) {  
			result =  "";  
		} else {
			if (value instanceof Users) {
				result = String.valueOf(((Users) value).getId());  
			} else if (value instanceof UsersTO) {
				result = String.valueOf(((UsersTO) value).getId());  	
			}

		}  
		return result;
	}
}