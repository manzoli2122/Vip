package vip.core.view;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


import vip.core.domain.UserType;

@FacesConverter("enumTypeConverter")
public class EnumTypeConverter  implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			
			for( UserType teste : UserType.values()){
				if(teste.getLabel().equals(value)){
					return teste;
				}
			}
			
			//return UserType.valueOf(value);
		}
		return null;
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof UserType) {
			return ((UserType) value).getLabel();
		}
		return null;
	}

}
