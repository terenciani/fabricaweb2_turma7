package br.com.fabricadeprogramador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.fabricadeprogramador.entidade.Cidade;
import br.com.fabricadeprogramador.service.ServiceException;
import br.com.fabricadeprogramador.service.CidadeService;

@Controller ("cidadeController")
public class CidadeController {
	private Cidade cidade = new Cidade();
	
	@Autowired
	private CidadeService cidadeService;

	public void salvar() {
		try {
			cidadeService.salvar(cidade);
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
}
