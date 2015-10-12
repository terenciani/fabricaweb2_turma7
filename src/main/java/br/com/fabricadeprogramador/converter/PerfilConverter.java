package br.com.fabricadeprogramador.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fabricadeprogramador.entidade.Perfil;
import br.com.fabricadeprogramador.service.PerfilService;

@Component
public class PerfilConverter implements Converter {

	@Autowired
	PerfilService perfilService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String id) {
		try {
			return perfilService.buscarPorId(Integer.parseInt(id));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object objPerfil) {
		try {
			if (objPerfil instanceof Perfil) {
				Perfil perfil = (Perfil) objPerfil;
				return perfil.getId().toString();
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}
