package com.sulasoftwares.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sulasoftwares.model.Cliente;
import com.sulasoftwares.repository.Clientes;
import com.sulasoftwares.util.cdi.CDIServiceLocator;


	@FacesConverter(forClass=Cliente.class)
	public class ClienteConverter implements Converter {

		//@Inject
		private Clientes clientes;
		
		public ClienteConverter() {
			this.clientes = (Clientes) CDIServiceLocator.getBean(Clientes.class);
		}
		
		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			Cliente retorno = null;
			
			if (value != null) {
				//Long id = new Long(value);
				retorno = this.clientes.porId(new Long(value));
			}
			
			return retorno;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			if (value != null) {
				//return ((Usuario) value).getId().toString();
				Cliente cliente = (Cliente) value;
	            return cliente.getId() == null ? null : cliente.getId().toString();
			}
			return "";
		}
}