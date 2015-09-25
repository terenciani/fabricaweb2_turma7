package br.com.fabricadeprogramador.dao;

import java.util.List;

import br.com.fabricadeprogramador.entidade.Cidade;

public interface CidadeDAO {
	public Cidade salvar(Cidade usuario);
	public void excluir(Cidade usuario) throws DAOException;
	public Cidade buscarPorId(int id);
	public List<Cidade> buscarTodas();
	public Cidade buscarPorNome(String nome);
}

