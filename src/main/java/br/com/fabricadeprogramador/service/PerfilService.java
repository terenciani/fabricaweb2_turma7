package br.com.fabricadeprogramador.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.fabricadeprogramador.dao.DAOException;
import br.com.fabricadeprogramador.dao.PerfilDAO;
import br.com.fabricadeprogramador.entidade.Perfil;

@Service ("perfilService")
public class PerfilService {

	@Autowired
	@Qualifier("perfilDAOJPA")
	PerfilDAO perfilDAO;
	
	@PersistenceContext
	private EntityManager em;

	public Perfil salvar(Perfil perfil) throws ServiceException {
	
		return perfilDAO.salvar(perfil);
	}
	
	public void excluir (Perfil perfil) throws ServiceException{
		try {
			perfilDAO.excluir(perfil);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Perfil> buscarTodos (){
		return perfilDAO.buscarTodos();
	}
	
	public Perfil buscarPorId(int id){
		return perfilDAO.buscarPorId(id);
	}
	
	
}
