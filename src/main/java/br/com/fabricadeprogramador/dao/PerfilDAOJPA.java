package br.com.fabricadeprogramador.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricadeprogramador.entidade.Perfil;

@Repository
public class PerfilDAOJPA implements PerfilDAO {

	@PersistenceContext
	EntityManager em;

	public PerfilDAOJPA() {

	}

	public PerfilDAOJPA(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public Perfil salvar(Perfil perfil) {
		Perfil perfilPersistido = em.merge(perfil);
		return perfilPersistido;
	}

	@Transactional
	public void excluir(Perfil perfil) throws DAOException {
		try {
			Perfil perfilManaged = em.getReference(Perfil.class, perfil.getId());
			em.remove(perfilManaged);
		} catch (Exception e) {
			throw new DAOException("Não foi possível excluir", e);
		}
	}

	public Perfil buscarPorId(int id) {
		return em.find(Perfil.class, id);
	}

	public List<Perfil> buscarTodos() {
		Query q = em.createQuery("select u from Perfil u");
		return q.getResultList();
	}
}
