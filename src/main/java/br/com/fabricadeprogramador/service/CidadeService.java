package br.com.fabricadeprogramador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.fabricadeprogramador.dao.DAOException;
import br.com.fabricadeprogramador.dao.CidadeDAO;
import br.com.fabricadeprogramador.entidade.Cidade;

@Service ("cidadeService")
public class CidadeService {
	@Autowired
	@Qualifier("cidadeDAOJPA")
	CidadeDAO cidadeDAO;

	public Cidade salvar(Cidade cidade) throws ServiceException {
		
		Cidade cidadeExistente = cidadeDAO.buscarPorNome(cidade.getNome());
		
		if (cidadeExistente != null){
			throw new ServiceException("Usuário já existe!");
			
		}
		return cidadeDAO.salvar(cidade);
	}
	
	public void excluir (Cidade cidade) throws ServiceException{
		try {
			cidadeDAO.excluir(cidade);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Cidade> buscarTodas (){
		return cidadeDAO.buscarTodas();
	}
	
	public Cidade buscarNome (String nome){
		return cidadeDAO.buscarPorNome(nome);
	}
	
	public Cidade buscarPorId(int id){
		return cidadeDAO.buscarPorId(id);
	}
	
}
