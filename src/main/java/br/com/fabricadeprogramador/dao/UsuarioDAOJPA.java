package br.com.fabricadeprogramador.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricadeprogramador.entidade.Usuario;

@Repository
public class UsuarioDAOJPA implements UsuarioDAO{

	@PersistenceContext
	EntityManager em;

	public UsuarioDAOJPA() {

	}

	public UsuarioDAOJPA(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public Usuario salvar(Usuario usuario) {
		Usuario usuarioPersistido = em.merge(usuario);
		return usuarioPersistido;
	}

	@Transactional
	public void excluir(Usuario usuario) throws DAOException {
		try {
			em.remove(usuario);
		} catch (IllegalArgumentException e) {
			throw new DAOException ("Não foi possível excluir", e);
		}
	}

	public Usuario buscarPorId(int id) {
		return em.find(Usuario.class, id);
	}

	public List<Usuario> buscarTodos() {
		Query q = em.createQuery("select u from Usuario u");
		return q.getResultList();
	}
}
