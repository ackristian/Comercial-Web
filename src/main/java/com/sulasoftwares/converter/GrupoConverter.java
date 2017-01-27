package com.sulasoftwares.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sulasoftwares.model.Grupo;
import com.sulasoftwares.repository.Grupos;
import com.sulasoftwares.util.cdi.CDIServiceLocator;


	@FacesConverter(forClass=Grupo.class)
	public class GrupoConverter implements Converter {

		 //@Inject Inject não funciona com conversores
		   private Grupos grupos;

		    public GrupoConverter() {
		        grupos = CDIServiceLocator.getBean(Grupos.class);
		    }
		    @Override
		    public Object getAsObject(FacesContext context, UIComponent component, String value) {
		        Grupo retorno = null;

		        if (value != null) {
		            String nome = new String(value);
		            retorno = grupos.grupoPorNome(nome);
		        }

		        return retorno;
		    }

		    @Override
		    public String getAsString(FacesContext context, UIComponent component, Object value) {

		        if (value != null) {
		            Grupo grupo = (Grupo) value;
		            //return grupo.getNome().toString();
		            return grupo.getNome() != null ? grupo.getNome().toString() : null;
		        }
		        return "";
		    }
		}