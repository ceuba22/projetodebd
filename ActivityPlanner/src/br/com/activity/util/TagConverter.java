package br.com.activity.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityNotFoundException;

import br.com.activity.facade.ActivityFacade;
import br.com.activity.tag.entidade.Tag;
import br.com.activity.tag.to.TagTO;

@FacesConverter(value = "tagConverter")
public class TagConverter implements Converter{
	@Override
	public Tag getAsObject(FacesContext facesContext, UIComponent uiComponent, String submittedValue) {
		Tag users = new Tag();
		if (submittedValue.trim().equals("")) {  
			return null;  
		} else {  
			try {  
				//				int number = Integer.parseInt(submittedValue); 
				try {
					users = ActivityFacade.getInstance().getTag(Long.parseLong(submittedValue));
				} catch (EntityNotFoundException e) {
					users = new Tag();
				} catch (Throwable e) {
					users = new Tag();
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
			if (value instanceof Tag) {
				result = String.valueOf(((Tag) value).getId());  
			} else if (value instanceof TagTO) {
				result = String.valueOf(((TagTO) value).getId());  	
			}

		}  
		return result;
	}
}
