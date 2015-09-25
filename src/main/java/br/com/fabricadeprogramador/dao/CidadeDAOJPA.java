package br.com.fabricadeprogramador.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricadeprogramador.entidade.Cidade;

@Repository
public class CidadeDAOJPA implements CidadeDAO {

	@PersistenceContext
	EntityManager em;

	public CidadeDAOJPA() {

	}

	public CidadeDAOJPA(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public Cidade salvar(Cidade cidade) {
		Cidade cidadePersistida = em.merge(cidade);
		return cidadePersistida;
	}

	@Transactional
	public void excluir(Cidade cidade) throws DAOException {
		try {
			em.remove(cidade);
		} catch (IllegalArgumentException e) {
			throw new DAOException("Não foi possível excluir", e);
		}
	}

	public Cidade buscarPorId(int id) {
		return em.find(Cidade.class, id);
	}

	public List<Cidade> buscarTodas() {
		Query q = em.createQuery("select u from Cidade u");
		return q.getResultList();
	}

	@Override
	public Cidade buscarPorNome(String nome) {
		Cidade cidade;
		try {
			Query q = em.createQuery("select u from Cidade u where u.nome=:nomeParam");
			q.setParameter("nomeParam", nome);
			q.setMaxResults(1);
			cidade = (Cidade) q.getSingleResult();
		} catch (NoResultException e) {
			cidade = null;
		}

		return cidade;
	}

}
