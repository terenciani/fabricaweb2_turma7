package br.com.fabricadeprogramador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.fabricadeprogramador.dao.UsuarioDAO;
import br.com.fabricadeprogramador.entidade.Usuario;

@Service ("usuarioService")
public class UsuarioService {

	@Autowired
	@Qualifier("usuarioDAOJPA")
	UsuarioDAO usuarioDAO;

	public Usuario salvar(Usuario usuario) throws ServiceException {
		
		Usuario usuarioExistente = usuarioDAO.buscarPorLogin(usuario.getLogin());
		
		if (usuarioExistente != null){
			throw new ServiceException("Usuário já existe!");
			
		}
		return usuarioDAO.salvar(usuario);
	}
}
