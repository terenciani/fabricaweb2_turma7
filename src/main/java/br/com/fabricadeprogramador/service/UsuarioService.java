package br.com.fabricadeprogramador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.fabricadeprogramador.dao.DAOException;
import br.com.fabricadeprogramador.dao.UsuarioDAO;
import br.com.fabricadeprogramador.entidade.Usuario;

@Service("usuarioService")
public class UsuarioService {

	@Autowired
	@Qualifier("usuarioDAOJPA")
	UsuarioDAO usuarioDAO;

	public Usuario salvar(Usuario usuario) throws ServiceException {

		if (usuario.getId() == null) {
			Usuario usuarioExistente = usuarioDAO.buscarPorLogin(usuario.getLogin());

			if (usuarioExistente != null) {
				throw new ServiceException("Usuário já existe!");
			}
		}
		return usuarioDAO.salvar(usuario);
	}

	public void excluir(Usuario usuario) throws ServiceException {
		try {
			usuarioDAO.excluir(usuario);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public List<Usuario> buscarTodos() {
		return usuarioDAO.buscarTodos();
	}

	public Usuario buscarPorLogin(String login) {
		return usuarioDAO.buscarPorLogin(login);
	}

	public Usuario buscarPorId(int id) {
		return usuarioDAO.buscarPorId(id);
	}

}
