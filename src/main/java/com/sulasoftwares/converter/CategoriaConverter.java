package com.sulasoftwares.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sulasoftwares.model.Categoria;
import com.sulasoftwares.repository.Categorias;
import com.sulasoftwares.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

	//@Inject
		private Categorias categorias;
		
		public CategoriaConverter() {
			categorias = CDIServiceLocator.getBean(Categorias.class);
		}
		
		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			Categoria retorno = null;
			
			if (value != null) {
				//Long id = new Long(value);
				retorno = this.categorias.porId(new Long(value));
			}
			
			return retorno;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			if (value != null) {
				//return ((Categoria) value).getId().toString();
				Categoria categoria = (Categoria) value;
				return categoria.getId() == null ? null : categoria.getId().toString();
			}
			
			return "";
		}


}
